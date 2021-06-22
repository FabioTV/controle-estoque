package com.controleestoque.repository;

import java.util.List;

import com.controleestoque.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    Boolean existsByNome(String nome);
    Produto findByNome(String nome);
    List<Produto> findByNomeLike(String nome);
}
