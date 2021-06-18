package com.controleestoque.service;

import java.util.List;

import com.controleestoque.dto.FornecedorDTO;

import org.springframework.http.ResponseEntity;

public interface FornecedorService {
    List<FornecedorDTO> listarFornecedores();
    FornecedorDTO listarFornecedor(Long id);
    ResponseEntity<?> cadastrarFornecedor(FornecedorDTO fornecedorDTO);
    ResponseEntity<?> editarFornecedor(FornecedorDTO fornecedorDTO);
    ResponseEntity<?> deletarFornecedor(Long id);
}
