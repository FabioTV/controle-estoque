package com.controleestoque.repository;

import com.controleestoque.model.Marca;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long>{
    Boolean existsByNome(String nome);
    Marca findByNome(String nome);
}
