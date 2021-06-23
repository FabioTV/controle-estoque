package com.controleestoque.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_produto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank
    private String nome;

    @NotBlank
    @Column(length = 10)
    private String unidadeMedida;

    @Column(name = "local_armazenado")
    private String localArmazenado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marca_id")
    @JsonBackReference(value = "marcaReference")
    private Marca marca;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    @JsonBackReference(value = "categoriaReference")
    private Categoria categoria;

    @OneToMany(mappedBy = "produto")
    @JsonIgnore
    private Set<MovimentoEntradaProduto> movimentos;

    //getters and setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<MovimentoEntradaProduto> getMovimentos() {
        return this.movimentos;
    }

    public void setMovimentos(Set<MovimentoEntradaProduto> movimentos) {
        this.movimentos = movimentos;
    }


}
