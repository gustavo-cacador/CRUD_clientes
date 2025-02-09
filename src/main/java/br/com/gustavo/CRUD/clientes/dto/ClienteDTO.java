package br.com.gustavo.CRUD.clientes.dto;

import br.com.gustavo.CRUD.clientes.entities.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ClienteDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "Nome precisa ter entre 3 e 80 caracteres.")
    @NotBlank(message = "Campo requerido")
    private String nome;

    @Size(min = 11, max = 11, message = "CPF precisa ter 11 caracteres.")
    @NotBlank(message = "Campo requerido")
    private String cpf;

    @Positive(message = "A renda precisa ser positiva.")
    private Double renda;

    @PastOrPresent(message = "A data de nascimento não pode estar no futuro.")
    private LocalDate dataNascimento;

    private Integer filhos;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nome, String cpf, Double renda, LocalDate dataNascimento, Integer filhos) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.renda = renda;
        this.dataNascimento = dataNascimento;
        this.filhos = filhos;
    }

    public ClienteDTO(Cliente entity) {
        id = entity.getId();
        nome = entity.getNome();
        cpf = entity.getCpf();
        renda = entity.getRenda();
        dataNascimento = entity.getDataNascimento();
        filhos = entity.getFilhos();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getRenda() {
        return renda;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Integer getFilhos() {
        return filhos;
    }
}
