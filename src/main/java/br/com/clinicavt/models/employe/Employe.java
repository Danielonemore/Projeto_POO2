package br.com.clinicavt.models.employe;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employe")
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(min = 5, message = "O nome deve conter no mínimo 5 caracteres.")
    private String nome;

    private String funcao;

    private BigDecimal salario;

    @Temporal(TemporalType.DATE)
    private LocalDate dataEntrada;

    private Boolean ativo;

    public Employe(EmployeDto colaboradorDto){
        this.dataEntrada = colaboradorDto.dataEntrada();
        this.nome = colaboradorDto.nome();
        this.funcao = colaboradorDto.funcao();
        this.salario = colaboradorDto.salario();
        this.ativo = true;
    }

    public Employe updateEmploye(EmployeUpdate dados){
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.funcao() != null){
            this.funcao = dados.funcao();
        }
        if (dados.salario() != null){
            this.salario = dados.salario();
        }
        return this;
    }

    public void delete(){
        this.ativo = false;
    }

}
