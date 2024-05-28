package br.com.clinicavt.controllers;

import br.com.clinicavt.models.email.EmailRecord;
import br.com.clinicavt.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping
    public ResponseEntity<String> sendEmail (@RequestBody @Valid EmailRecord email){
        service.sendEMail(email);
        return ResponseEntity.status(HttpStatus.OK).body("Email enviado");
    }



}
