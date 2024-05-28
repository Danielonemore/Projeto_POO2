package br.com.clinicavt.services;

import br.com.clinicavt.models.consult.Consult;
import br.com.clinicavt.models.consult.ConsultDto;
import br.com.clinicavt.models.consult.ConsultUpdate;
import br.com.clinicavt.models.email.EmailRecord;
import br.com.clinicavt.repositories.ConsultRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConsultService {

    @Autowired
    ConsultRepository repository;
    @Autowired
    EmailService emailService;

    public List<Consult> getAll(){
        return repository.findAll();
    }

    public Optional<Consult> getById(UUID id){
        return repository.findById(id);
    }

    public Consult create(ConsultDto consult) {
        LocalDate date = consult.data();
        LocalTime hour = consult.hour();

        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            throw new RuntimeException("Consultas só podem ser agendadas em dias úteis");
        }

        if ((hour.isBefore(LocalTime.of(9, 0)) || hour.isAfter(LocalTime.of(12, 0))) &&
                (hour.isBefore(LocalTime.of(14, 0)) || hour.isAfter(LocalTime.of(19, 0)))) {
            throw new RuntimeException("Consultas só podem ser agendadas entre 9am-12pm e 14pm-19pm");
        }

        Optional<Consult> nearbyConsult = repository.findNearbyConsult(date, hour.getHour());
        if (nearbyConsult.isPresent()) {
            Optional<LocalTime> nextAvailableTime = repository.findNextAvailableTime(date, hour);
            if (nextAvailableTime.isPresent()) {
                throw new RuntimeException("Não é possível agendar uma consulta tão próxima a outra. O próximo horário disponível é " + nextAvailableTime.get());
            } else {
                Optional<LocalDate> nextAvailableDay = repository.findNextAvailableDay(date);
                throw new RuntimeException("Não há horários disponíveis neste dia. O próximo dia disponível é " + nextAvailableDay.get());
            }
        }

        if (consult.veterinarian().getEmail() != null) {
            String messageToVeterinarian = String.format("Olá %s, você tem uma consulta agendada para o dia %s às %s com o cliente %s. Bom trabalho! :)",
                    consult.veterinarian().getName(), date, hour, consult.client().getNome());
            emailService.sendEMail(new EmailRecord(consult.veterinarian().getEmail(), "Nova Consulta Agendada", messageToVeterinarian));
        } else emailService.sendEMail(new EmailRecord("joao_prestupa11@hotmail.com", "Erro ao enviar email", "O email não foi enviado."));

        if (consult.client().getEmail() != null) {
            String messageToClient = String.format("Olá %s, sua consulta foi agendada para o dia %s às %s com o veterinário %s. Nos vemos lá ! :)",
                    consult.client().getNome(), date, hour, consult.veterinarian().getName() == null ? "a ser definido" : consult.veterinarian().getName());
            emailService.sendEMail(new EmailRecord(consult.client().getEmail(), "Consulta Agendada", messageToClient));
        } else emailService.sendEMail(new EmailRecord("joao_prestupa11@hotmail.com", "Erro ao enviar email", "O email não foi enviado."));

        return repository.save(new Consult(consult));
    }

    public Consult update(UUID id, ConsultUpdate consultUpdate){
        return repository.getReferenceById(id).updateConsulta(consultUpdate);
    }

    public void cancelConsult(UUID id){
        repository.getReferenceById(id).cancelConsult();
    }

}
