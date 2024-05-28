package br.com.clinicavt.services;

import br.com.clinicavt.models.adresses.AdressesEmbeddable;
import br.com.clinicavt.models.adresses.AdressesUpdate;
import br.com.clinicavt.models.veterinarian.Veterinarian;
import br.com.clinicavt.models.veterinarian.VeterinarianDto;
import br.com.clinicavt.models.veterinarian.VeterinarianUpdate;
import br.com.clinicavt.repositories.VeterinarianRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VeterinarianService {

    @Autowired
    private VeterinarianRepository repository;

    public List<Veterinarian> getAll() {
        return repository.findAll();
    }

    public Optional<Veterinarian> getById(UUID id) {
        return repository.findById(id);
    }

    public Veterinarian create(VeterinarianDto veterinanian) {
        return repository.save(new Veterinarian(veterinanian));
    }

    public Veterinarian update(UUID id, VeterinarianUpdate veterinarianUpdate) {
        return repository.getReferenceById(id).updateVeterinarian(veterinarianUpdate);
    }

    public void delete(UUID id){
        repository.getReferenceById(id).delete();
    }

}
