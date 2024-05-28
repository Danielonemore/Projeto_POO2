package br.com.clinicavt.controllers;

import br.com.clinicavt.models.consult.Consult;
import br.com.clinicavt.models.consult.ConsultDto;
import br.com.clinicavt.models.consult.ConsultUpdate;
import br.com.clinicavt.services.ConsultService;
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
@RequestMapping("clinicavt/consult")
public class ConsultController {

    @Autowired
    ConsultService service;

    @GetMapping
    public ResponseEntity<List<Consult>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Consult>> getById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Consult> create(@RequestBody @Valid ConsultDto consultDto) {
        var consultCreated = service.create(consultDto);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consultCreated.getId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).body(consultCreated);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Consult> update(@PathVariable @Value("id") UUID id, @RequestBody @Valid ConsultUpdate consultUpdate) {
        var consultFound = service.update(id, consultUpdate);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consultFound.getId()).toUri();

        return ResponseEntity.status(HttpStatus.OK).body(consultFound);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity cancelConsult(@PathVariable UUID id) {
        service.cancelConsult(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
