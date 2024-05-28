package br.com.clinicavt.controllers;

import br.com.clinicavt.models.vaccine.Vaccine;
import br.com.clinicavt.models.vaccine.VaccineDto;
import br.com.clinicavt.models.vaccine.VaccineUpdate;
import br.com.clinicavt.services.VaccineService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clinicavt/vaccine")
public class VaccineController {

    @Autowired
    VaccineService service;

    @GetMapping
    public ResponseEntity<List<Vaccine>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vaccine>> getById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Vaccine> create(@RequestBody @Valid VaccineDto vaccineDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(vaccineDto));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Vaccine> update(@PathVariable @Value("id") UUID id, @RequestBody @Valid VaccineUpdate vaccineUpdate) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, vaccineUpdate));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
