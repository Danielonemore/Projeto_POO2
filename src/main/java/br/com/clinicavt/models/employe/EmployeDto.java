package br.com.clinicavt.models.employe;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record EmployeDto(LocalDate dataEntrada, String nome, String funcao, BigDecimal salario) {
}
