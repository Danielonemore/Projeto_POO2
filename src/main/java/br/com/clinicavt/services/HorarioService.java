package br.com.clinicavt.services;

import br.com.clinicavt.models.horario.Horario;
import br.com.clinicavt.models.horario.HorarioDto;
import br.com.clinicavt.models.horario.HorarioUpdate;
import br.com.clinicavt.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HorarioService {

    @Autowired
    HorarioRepository repository;

    public List<Horario> getAll(){
        return repository.findAll();
    }

    public Optional<Horario> getById(UUID id){
        return repository.findById(id);
    }

    public Horario create(HorarioDto data){
        return repository.save(new Horario(data));
    }

    public Horario updateHorario(UUID id, HorarioUpdate data){return repository.getReferenceById(id).horarioUpdate(data);}

    public void delete(UUID id){
        repository.getReferenceById(id).delete();
    }

}
