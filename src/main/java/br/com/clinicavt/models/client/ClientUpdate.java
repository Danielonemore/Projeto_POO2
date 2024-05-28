package br.com.clinicavt.models.client;

import br.com.clinicavt.models.adresses.AdressesEmbeddable;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record ClientUpdate(
        String nome,
        AdressesEmbeddable endereco,
        String email,
        String telefone,
        LocalDate nascimento,
        String cpf) {
}
