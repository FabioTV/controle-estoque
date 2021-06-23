package com.controleestoque.service;

import java.util.List;
import com.controleestoque.dto.EntradaDTO;

import org.springframework.http.ResponseEntity;

public interface EntradaService {
    List<EntradaDTO> listarEntradas();
    EntradaDTO listarEntrada(Long id);
    ResponseEntity<?> cadastrarEntrada(EntradaDTO entradaDTO);
    ResponseEntity<?> editarEntrada(EntradaDTO entradaDTO);
    ResponseEntity<?> deletarEntrada(Long id);
}
