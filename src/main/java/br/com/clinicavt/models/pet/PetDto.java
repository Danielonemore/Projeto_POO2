package br.com.clinicavt.models.pet;

import br.com.clinicavt.models.client.Client;
import br.com.clinicavt.models.vaccine.Vaccine;

import java.time.LocalDate;
import java.util.List;

public record PetDto(String nome, String descricaoPet, PetEnum animal, String raca, LocalDate nascimento,
                     String welfare_animal, Client client, List<Vaccine> vaccines) {
}
