package com.controleestoque.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tb_categoria")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String nome;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
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
