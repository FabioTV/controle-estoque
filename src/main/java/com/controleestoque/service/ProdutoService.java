package com.controleestoque.service;

import java.util.List;

import com.controleestoque.dto.ProdutoDTO;

import org.springframework.http.ResponseEntity;

public interface ProdutoService {
    List<ProdutoDTO> listarProdutos();
    List<ProdutoDTO> listarProdutos(String nome);
    ProdutoDTO listarProduto(Long id);
    ResponseEntity<?> cadastrarProduto(ProdutoDTO produtoDTO);
    ResponseEntity<?> editarProduto(ProdutoDTO produtoDTO);
    ResponseEntity<?> deletarProduto(Long id);
}
