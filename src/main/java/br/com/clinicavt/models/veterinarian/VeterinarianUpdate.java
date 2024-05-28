package br.com.clinicavt.models.veterinarian;

import java.math.BigDecimal;

public record VeterinarianUpdate(String name, String email, String crmv, BigDecimal salary, VeterinarianDetailsEnum veterinanDetails) {
}
