package br.com.clinicavt.models.client;

import br.com.clinicavt.models.adresses.AdressesEmbeddable;

import java.time.LocalDate;
import java.util.Date;

public record ClientDto(String nome,
                        LocalDate dataCadastro,
                        AdressesEmbeddable endereco,
                        String email,
                        String telefone,
                        LocalDate nascimento,
                        String cpf) {
}
