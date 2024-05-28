package br.com.clinicavt.models.consult;


import br.com.clinicavt.models.client.Client;
import br.com.clinicavt.models.pet.Pet;
import br.com.clinicavt.models.veterinarian.Veterinarian;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Consult")
@Table(name = "consult")
public class Consult {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @Column(columnDefinition = "TIME")
    private LocalTime time;

    private String description;

    private BigDecimal value;

    private Boolean cancel;

    private Boolean ativo;

    @ManyToOne
    private Client client;
    @ManyToOne
    private Pet pet;
    @ManyToOne
    private Veterinarian veterinarian;

    public void desative(){
        this.ativo = false;
    }

    public void cancelConsult(){
        this.cancel = true;
    }

    public Consult(ConsultDto consultaDto) {
        this.cancel = false;
        this.date = consultaDto.data();
        this.time = consultaDto.hour();
        this.description = consultaDto.description();
        this.value = consultaDto.value();
        this.client = consultaDto.client();
        this.pet = consultaDto.pet();
        this.veterinarian = consultaDto.veterinarian();
        this.ativo = true;
    }

    public Consult updateConsulta(ConsultUpdate dados){
        if (dados.date() != null){
            this.date = dados.date();
        }
        if (dados.hour() != null){
            this.time = dados.hour();
        }
        if (dados.description() != null){
            this.description = dados.description();
        }
        if (dados.value() != null){
            this.value = dados.value();
        }
        if (dados.client() != null){
            this.client = dados.client();
        }
        if (dados.pet() != null){
            this.pet = dados.pet();
        }
        if (dados.veterinarian() != null){
            this.veterinarian = dados.veterinarian();
        }
        return this;
    }


}
