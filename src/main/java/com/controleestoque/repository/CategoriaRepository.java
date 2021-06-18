package com.controleestoque.repository;

import com.controleestoque.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    Boolean existsByNome(String nome);
    Categoria findByNome(String nome);
    
}
