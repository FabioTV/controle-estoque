package com.controleestoque.repository;

import com.controleestoque.model.Unidade;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeRepository extends JpaRepository<Unidade, Long>{
    
}
