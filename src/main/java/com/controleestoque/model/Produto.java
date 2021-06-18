package com.controleestoque.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tb_produto")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotEmpty
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    @NotEmpty
    private Categoria categoria;

    //getters and setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
