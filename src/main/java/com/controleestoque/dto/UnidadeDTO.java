package com.controleestoque.dto;

import javax.validation.constraints.NotBlank;

import com.controleestoque.model.Empresa;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UnidadeDTO {
    private Long id;
    @NotBlank
    private String nome;
    private Empresa empresa;

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

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }


}
