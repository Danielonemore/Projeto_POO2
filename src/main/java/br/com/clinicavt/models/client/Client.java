package br.com.clinicavt.models.client;


import br.com.clinicavt.models.adresses.AdressesEmbeddable;
import br.com.clinicavt.models.consult.Consult;
import br.com.clinicavt.models.pet.Pet;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Client")
@Table(name = "client")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @Size(min = 5, message = "Um nome deve conter no mínimo 5 caracteres.")
    private String nome;
    @Temporal(TemporalType.DATE)
    private LocalDate dataCadastro;
    @Embedded
    private AdressesEmbeddable endereco;
    @Email(message = "Deve ser inserido um endereço de e-mail válido.")
    @Column(unique = true)
    private String email;

    private String telefone;
    @Temporal(TemporalType.DATE)
    private LocalDate nascimento;

    @NotBlank
    @Column(unique = true)
    private String cpf;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Pet> pets;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Consult> consults;

    private Boolean ativo;

    public Client updateClients (ClientUpdate dados){
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.endereco() != null){
            this.endereco = dados.endereco();
        }
        if (dados.email() != null){
            this.email = dados.email();
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if (dados.nascimento() != null){
            this.nascimento = dados.nascimento();
        }
        if (dados.cpf() != null){
            this.cpf = dados.cpf();
        }
        return this;
    }

    public Client(ClientDto clinicaDto){
        this.nome = clinicaDto.nome();
        this.dataCadastro = clinicaDto.dataCadastro();
        this.email = clinicaDto.email();
        this.telefone = clinicaDto.telefone();
        this.nascimento = clinicaDto.nascimento();
        this.cpf = clinicaDto.cpf();
        this.endereco = clinicaDto.endereco();
        this.ativo = true;
    }

    public void delete(){
        this.ativo = false;
    }

}
