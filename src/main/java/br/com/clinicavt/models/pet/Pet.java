package br.com.clinicavt.models.pet;

import br.com.clinicavt.models.client.Client;
import br.com.clinicavt.models.consult.Consult;
import br.com.clinicavt.models.vaccine.Vaccine;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serial;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "pet")
@Table(name = "Pet")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Pet {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String nome;

    private String descricaoPet;

    @Enumerated(EnumType.STRING)
    private PetEnum animal;

    private String raca;

    @Temporal(TemporalType.DATE)
    private LocalDate nascimento;

    private String welfare_animal;

    @ManyToOne
    private Client client;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Vaccine> vaccines;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Consult> consults;

    private Boolean ativo;

    public Pet(PetDto petsDto) {
        this.nome = petsDto.nome();
        this.descricaoPet = petsDto.descricaoPet();
        this.animal = petsDto.animal();
        this.raca = petsDto.raca();
        this.nascimento = petsDto.nascimento();
        this.welfare_animal = petsDto.welfare_animal();
        this.client = petsDto.client();
        this.vaccines = petsDto.vaccines();
        this.ativo = true;
    }

    public Pet updatePet(PetUpdate dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.descricaoPet() != null) {
            this.descricaoPet = dados.descricaoPet();
        }
        if (dados.animal() != null) {
            this.animal = dados.animal();
        }
        if (dados.raca() != null) {
            this.raca = dados.raca();
        }
        if (dados.nascimento() != null) {
            this.nascimento = dados.nascimento();
        }
        if (dados.welfare_animal() != null) {
            this.welfare_animal = dados.welfare_animal();
        }
        if (dados.client() != null) {
            this.client = dados.client();
        }
        if (dados.vaccines() != null) this.vaccines = dados.vaccines();
        return this;
    }

    public void delete() {
        this.ativo = false;
    }

}
