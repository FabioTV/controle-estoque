package com.controleestoque.repository;

import java.util.List;

import com.controleestoque.model.Entrada;
import com.controleestoque.model.MovimentoEntradaProduto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentoEntradaProdutoRepository extends JpaRepository<MovimentoEntradaProduto, Long>{
    List<MovimentoEntradaProduto> findByEntrada(Entrada entrada);
}
