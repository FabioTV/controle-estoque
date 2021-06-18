package com.controleestoque.repository;

import com.controleestoque.model.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,Long>{
    Empresa findByCnpj(String cnpj);
    Boolean existsByCnpj(String cnpj);
    Empresa findByRazaoSocial(String razaoSocial);
    Boolean existsByRazaoSocial(String razaoSocial);
}
