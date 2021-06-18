package com.controleestoque.service;

import java.util.List;

import com.controleestoque.dto.CategoriaDTO;

import org.springframework.http.ResponseEntity;

public interface CategoriaService {
    List<CategoriaDTO> listarCategorias();
    CategoriaDTO listarCategoria(Long id);
    ResponseEntity<?> cadastrarCategoria(CategoriaDTO categoriaDTO);
    ResponseEntity<?> editarCategoria(CategoriaDTO categoriaDTO);
    ResponseEntity<?> deletarCategoria(Long id);
}
