package br.com.clinicavt.models.veterinarian;

import br.com.clinicavt.models.adresses.AdressesEmbeddable;
import br.com.clinicavt.models.consult.Consult;
import br.com.clinicavt.models.horario.Horario;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity(name = "veterinarian")
@Table(name = "Veterinarian")
public class Veterinarian {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(min = 5, message = "Um nome deve conter no mínimo 5 caracteres.")
    private String name;

    @Embedded
    private AdressesEmbeddable adress;

    @Email(message = "Um veterinário deve conter um endereço de email válido.")
    private String email;

    @NotNull(message = "Um veterinário deve ter um crmv registrado e válido.")
    private String crmv;

    @Temporal(TemporalType.DATE)
    private LocalDate data;

    private BigDecimal salary;

    private Boolean ativo;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Consult> consults;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Horario> horario;

    @Enumerated(EnumType.STRING)
    private VeterinarianDetailsEnum veterinarianDetails;

    public Veterinarian(VeterinarianDto veterinarianDto){
        this.name = veterinarianDto.name();
        this.data = veterinarianDto.data();
        this.adress = veterinarianDto.adress();
        this.email = veterinarianDto.email();
        this.crmv = veterinarianDto.crmv();
        this.salary = veterinarianDto.salary();
        this.veterinarianDetails = veterinarianDto.veterinanDetails();
        this.ativo = true;
    }

    public Veterinarian updateVeterinarian(VeterinarianUpdate data){
        if (data.name() != null){
            this.name = data.name();
        }
        if (data.email() != null){
            this.email = data.email();
        }
        if (data.crmv() != null){
            this.crmv = data.crmv();
        }
        if (data.salary() != null){
            this.salary = data.salary();
        }
        if (data.veterinanDetails() != null){
            this.veterinarianDetails = data.veterinanDetails();
        }
        return this;
    }

    public void delete(){
        this.ativo = false;
    }

}
