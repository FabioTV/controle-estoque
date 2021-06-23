package com.controleestoque.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.controleestoque.model.Empresa;
import com.controleestoque.model.Fornecedor;
import com.controleestoque.model.MovimentoEntradaProduto;

import lombok.Builder;

@Builder
public class EntradaDTO {

    private Long id;
    private LocalDateTime createdAt;
    private String notaFiscal;
    private Empresa empresa;
    private Fornecedor fornecedor;
    private Set<MovimentoEntradaProduto> movimentos;

    //Getters and Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Fornecedor getFornecedor() {
        return this.fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Set<MovimentoEntradaProduto> getMovimentos() {
        return this.movimentos;
    }

    public void setMovimentos(Set<MovimentoEntradaProduto> movimentos) {
        this.movimentos = movimentos;
    }

    public String getNotaFiscal() {
        return this.notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
    
}
