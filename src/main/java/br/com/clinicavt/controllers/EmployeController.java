package br.com.clinicavt.controllers;

import br.com.clinicavt.models.employe.Employe;
import br.com.clinicavt.models.employe.EmployeDto;
import br.com.clinicavt.models.employe.EmployeUpdate;
import br.com.clinicavt.services.EmployeService;
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
@RequestMapping("clinicavt/employe")
public class EmployeController {

    @Autowired
    private EmployeService service;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employe>> getById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Employe>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Employe> create(@RequestBody @Valid EmployeDto employe) {
        var employeCreate = service.create(employe);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeCreate.getId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(employeCreate);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Employe> update(@PathVariable @Value("id") UUID id, @RequestBody @Valid EmployeUpdate employes) {
        var employe = service.update(id, employes);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employe.getId()).toUri();

        return ResponseEntity.status(HttpStatus.OK).location(location).body(employe);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
