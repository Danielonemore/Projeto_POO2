package br.com.clinicavt.models.consult;

import br.com.clinicavt.models.client.Client;
import br.com.clinicavt.models.pet.Pet;
import br.com.clinicavt.models.veterinarian.Veterinarian;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultUpdate(LocalDate date, String description, BigDecimal value, Client client, Pet pet, Veterinarian veterinarian, LocalTime hour) {
}
