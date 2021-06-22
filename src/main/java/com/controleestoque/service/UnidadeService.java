package com.controleestoque.service;

import java.util.List;

import com.controleestoque.dto.UnidadeDTO;

import org.springframework.http.ResponseEntity;

public interface UnidadeService {
    List<UnidadeDTO> listarUnidades();
    UnidadeDTO listarUnidade(Long id);
    ResponseEntity<?> cadastrarUnidade(UnidadeDTO unidadeDTO);
    ResponseEntity<?> editarUnidade(UnidadeDTO unidadeDTO);
    ResponseEntity<?> deletarUnidade(Long id);
}
