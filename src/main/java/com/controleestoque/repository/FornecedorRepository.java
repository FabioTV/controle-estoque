package com.controleestoque.repository;

import com.controleestoque.model.Fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{
    Boolean existsByCnpj(String cnpj);
    Fornecedor findByCnpj(String cnpj);
}
