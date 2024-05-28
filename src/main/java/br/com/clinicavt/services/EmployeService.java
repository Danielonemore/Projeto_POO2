package br.com.clinicavt.services;

import br.com.clinicavt.models.employe.Employe;
import br.com.clinicavt.models.employe.EmployeDto;
import br.com.clinicavt.models.employe.EmployeUpdate;
import br.com.clinicavt.repositories.EmployeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeService {

    @Autowired
    EmployeRepository repository;

    public List<Employe> getAll(){
        return repository.findAll();
    }

    public Optional<Employe> getById(UUID id){
        return repository.findById(id);
    }

    public Employe create (EmployeDto colaborador){
        return repository.save(new Employe(colaborador));
    }

    public Employe update(UUID id, EmployeUpdate employeUpdate){
        return repository.getReferenceById(id).updateEmploye(employeUpdate);
    }

    public void delete(UUID id){
        repository.getReferenceById(id).delete();
    }

}
