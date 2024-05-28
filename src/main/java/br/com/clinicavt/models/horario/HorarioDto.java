package br.com.clinicavt.models.horario;

import br.com.clinicavt.models.veterinarian.Veterinarian;

import java.time.LocalDate;

public record HorarioDto(LocalDate dataDisponivel, Veterinarian veterinarian) {
}
