package com.controleestoque.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.controleestoque.model.Produto;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MarcaDTO {
    private Long id;
    @NotEmpty
    private String nome;
    private List<Produto> produtos;

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

    public List<Produto> getProdutos() {
        return this.produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
