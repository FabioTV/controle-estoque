package com.controleestoque.service;

import java.util.List;

import com.controleestoque.dto.MarcaDTO;

import org.springframework.http.ResponseEntity;

public interface MarcaService {
    List<MarcaDTO> listarMarcas();
    MarcaDTO listarMarca(Long id);
    ResponseEntity<?> cadastrarMarca(MarcaDTO marcaDTO);
    ResponseEntity<?> editarMarca(MarcaDTO marcaDTO);
    ResponseEntity<?> deletarMarca(Long id);
}
