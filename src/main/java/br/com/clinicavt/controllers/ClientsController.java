package br.com.clinicavt.controllers;

import br.com.clinicavt.models.client.Client;
import br.com.clinicavt.models.client.ClientDto;
import br.com.clinicavt.models.client.ClientUpdate;
import br.com.clinicavt.services.ClientService;
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
@RequestMapping("clinicavt/clients")
public class ClientsController {

    @Autowired
    ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> getById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody @Valid ClientDto clients) {
        var clientCreate = service.create(clients);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientCreate.getId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(clientCreate);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Client> update(@PathVariable @Value("id") UUID id, @RequestBody @Valid ClientUpdate clients) {
        var clientFound = service.update(id, clients);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientFound.getId()).toUri();

        return ResponseEntity.status(HttpStatus.OK).location(location).body(clientFound);
    }

}
