package br.com.clinicavt.controllers;

import br.com.clinicavt.models.horario.Horario;
import br.com.clinicavt.models.horario.HorarioDto;
import br.com.clinicavt.models.horario.HorarioUpdate;
import br.com.clinicavt.services.HorarioService;
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
@RequestMapping("clinicavt/horario")
public class HorarioController {

    @Autowired
    HorarioService service;

    @GetMapping
    public ResponseEntity<List<Horario>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Horario>> getById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Horario> create(@RequestBody @Valid HorarioDto data){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario> update(@PathVariable @Value("id") UUID id, @RequestBody @Valid HorarioUpdate data){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateHorario(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
