package br.com.clinicavt.models.veterinarian;

import br.com.clinicavt.models.adresses.AdressesEmbeddable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record VeterinarianDto(String name, String crmv, String email, AdressesEmbeddable adress, LocalDate data, BigDecimal salary, VeterinarianDetailsEnum veterinanDetails) {
}
