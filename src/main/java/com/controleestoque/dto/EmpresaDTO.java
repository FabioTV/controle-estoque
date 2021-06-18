package com.controleestoque.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.controleestoque.model.Unidade;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmpresaDTO {
    private Long id;
    @NotBlank(message = "CNPJ inválido")
    private String cnpj;
    @NotBlank(message = "Razão social inválida!")
    private String razaoSocial;
    private List<Unidade> unidades;

    //Getters and Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public List<Unidade> getUnidades() {
        return this.unidades;
    }

    public void setUnidades(List<Unidade> unidades) {
        this.unidades = unidades;
    }
    
    
}
