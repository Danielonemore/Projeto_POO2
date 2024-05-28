package br.com.clinicavt.controllers;

import br.com.clinicavt.models.adresses.AdressesUpdate;
import br.com.clinicavt.models.veterinarian.Veterinarian;
import br.com.clinicavt.models.veterinarian.VeterinarianDto;
import br.com.clinicavt.models.veterinarian.VeterinarianUpdate;
import br.com.clinicavt.repositories.VeterinarianRepository;
import br.com.clinicavt.services.VeterinarianService;
import jakarta.transaction.Transactional;
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
@RequestMapping("clinicavt/veterinarian")
public class VeterinarianController {

    @Autowired
    private VeterinarianService service;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veterinarian>> getById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Veterinarian>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Veterinarian> create(@RequestBody VeterinarianDto veterinarian){
        var veterinarianCreate = service.create(veterinarian);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(veterinarianCreate.getId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(veterinarianCreate);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Veterinarian> update(@PathVariable @Value ("id") UUID id, @RequestBody VeterinarianUpdate veterinarianUpdate){
        var veterian = service.update(id, veterinarianUpdate);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(veterian.getId()).toUri();

        return ResponseEntity.status(HttpStatus.OK).location(location).body(veterian);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
