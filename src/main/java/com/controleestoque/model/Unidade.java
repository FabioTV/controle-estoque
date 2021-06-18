package com.controleestoque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "tb_unidade")
public class Unidade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String nome;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    //Getters and Setters
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
