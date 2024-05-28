package br.com.clinicavt.repositories;

import br.com.clinicavt.models.veterinarian.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface VeterinarianRepository extends JpaRepository<Veterinarian, UUID> {
}
