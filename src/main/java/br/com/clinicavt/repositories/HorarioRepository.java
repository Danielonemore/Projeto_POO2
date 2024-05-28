package br.com.clinicavt.repositories;

import br.com.clinicavt.models.horario.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HorarioRepository extends JpaRepository<Horario, UUID> {
}
