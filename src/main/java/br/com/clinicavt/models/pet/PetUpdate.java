package br.com.clinicavt.models.pet;

import br.com.clinicavt.models.client.Client;
import br.com.clinicavt.models.vaccine.Vaccine;

import java.time.LocalDate;
import java.util.List;

public record PetUpdate(
        String nome,
        Client client,
        Boolean ativo,
        String descricaoPet,
        PetEnum animal,
        String raca,
        LocalDate nascimento ,
        String welfare_animal,
        List<Vaccine> vaccines) {
}
