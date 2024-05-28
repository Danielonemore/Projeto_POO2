package br.com.clinicavt.models.vaccine;

import br.com.clinicavt.models.pet.Pet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity(name = "vaccine")
@Table(name = "vaccine")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String vaccine;

    @ManyToOne
    private Pet pet;

    private Boolean ativo;

    public Vaccine(VaccineDto data) {
        this.vaccine = data.vaccine();
        this.pet = data.pet();
        this.ativo = true;
    }

    public Vaccine updateVaccine(VaccineUpdate data) {
        if (data.vaccine() != null) this.vaccine = data.vaccine();
        if (data.pet() != null) this.pet = data.pet();
        return this;
    }

    public void delete(){
        this.ativo = false;
    }

}
