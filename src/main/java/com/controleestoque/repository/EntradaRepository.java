package com.controleestoque.repository;

import com.controleestoque.model.Entrada;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntradaRepository extends JpaRepository<Entrada, Long>{
    
}
