package br.com.clinicavt.services;

import br.com.clinicavt.models.pet.Pet;
import br.com.clinicavt.models.pet.PetDto;
import br.com.clinicavt.models.pet.PetUpdate;
import br.com.clinicavt.repositories.PetRepository;
import br.com.clinicavt.repositories.VaccineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetService {

    @Autowired
    PetRepository repository;

    public List<Pet> findAll() {
        return repository.findAll();
    }

    public Optional<Pet> getById(UUID id) {
        return repository.findById(id);
    }

    public Pet create(PetDto pet) {
        return repository.save(new Pet(pet));
    }

    public Pet update(UUID id, PetUpdate petUpdate) {
        return repository.getReferenceById(id).updatePet(petUpdate);
    }

    public void delete(UUID id){
        repository.getReferenceById(id).delete();
    }

}


