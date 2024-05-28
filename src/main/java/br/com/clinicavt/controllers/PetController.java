package br.com.clinicavt.controllers;

import br.com.clinicavt.models.pet.Pet;
import br.com.clinicavt.models.pet.PetDto;
import br.com.clinicavt.models.pet.PetUpdate;
import br.com.clinicavt.repositories.PetRepository;
import br.com.clinicavt.services.PetService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("clinicavt/pet")
public class PetController {

    @Autowired
    private PetService service;
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pet>> getById (@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public  ResponseEntity<Pet> create (@RequestBody @Valid PetDto pet){
        var petInserted = service.create(pet);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(petInserted.getId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(petInserted);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Pet> update (@PathVariable @Value ("id") UUID id, @RequestBody @Valid PetUpdate pets){
        var petFound = service.update(id, pets);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.status(HttpStatus.OK).location(location).body(petFound);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Optional<Pet>> delete (@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
