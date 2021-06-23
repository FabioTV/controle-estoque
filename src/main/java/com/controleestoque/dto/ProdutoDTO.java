package com.controleestoque.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.controleestoque.model.Categoria;
import com.controleestoque.model.Marca;

import lombok.Builder;

@Builder
public class ProdutoDTO {
    private Long id;
    
    @NotBlank
    private String nome;

    @NotBlank
    private String unidadeMedida;

    @NotEmpty
    private String localArmazenado;

    private Marca marca;

    private Categoria categoria;

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

    public String getUnidadeMedida() {
        return this.unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getLocalArmazenado() {
        return this.localArmazenado;
    }

    public void setLocalArmazenado(String localArmazenado) {
        this.localArmazenado = localArmazenado;
    }


    public Marca getMarca() {
        return this.marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
