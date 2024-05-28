package br.com.clinicavt.models.horario;

import br.com.clinicavt.models.veterinarian.Veterinarian;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "horario")
@Table(name = "horario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Temporal(TemporalType.DATE)
    private LocalDate dataDisponivel;

    @ManyToOne
    private Veterinarian veterinarian;

    private Boolean active;
    public Horario(HorarioDto data){
        this.dataDisponivel = data.dataDisponivel();
        this.veterinarian = data.veterinarian();
        this.active = true;
    }

    public void delete(){
        this.active = false;
    }

    public Horario horarioUpdate(HorarioUpdate data){
        if (data.dataDisponivel() != null) this.dataDisponivel = data.dataDisponivel();
        if (data.veterinarian() != null) this.veterinarian = data.veterinarian();
        return this;
    }



}
