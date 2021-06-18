package com.controleestoque.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FornecedorDTO {
    private Long id;
    @NotBlank
    private String nome;
    private String cnpj;    
    @Email(message = "E-mail inválido!")
    private String email;
    private String telefone;

    //Getters and Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}
