package com.controleestoque.repository;

import com.controleestoque.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
