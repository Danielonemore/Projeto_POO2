package br.com.clinicavt.services;

import br.com.clinicavt.models.vaccine.Vaccine;
import br.com.clinicavt.models.vaccine.VaccineDto;
import br.com.clinicavt.models.vaccine.VaccineUpdate;
import br.com.clinicavt.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VaccineService {

    @Autowired
    VaccineRepository repository;

    public List<Vaccine> getAll(){
        return repository.findAll();
    }

    public Optional<Vaccine> getById(UUID id){
        return repository.findById(id);
    }

    public Vaccine create(VaccineDto vaccineDto){
        return repository.save(new Vaccine(vaccineDto));
    }

    public Vaccine update(UUID id, VaccineUpdate vaccineUpdate){
        return repository.getReferenceById(id).updateVaccine(vaccineUpdate);
    }

    public void delete(UUID id){
        repository.getReferenceById(id).delete();
    }

}
