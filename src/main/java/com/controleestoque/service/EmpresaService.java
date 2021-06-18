package com.controleestoque.service;

import java.util.List;

import com.controleestoque.dto.EmpresaDTO;

import org.springframework.http.ResponseEntity;


public interface EmpresaService {
    List<EmpresaDTO> listarEmpresas();
    EmpresaDTO listarEmpresa(Long id);
    ResponseEntity<?> cadastrarEmpresa(EmpresaDTO empresaDTO);
    ResponseEntity<?> editarEmpresa(EmpresaDTO empresaDTO);
    ResponseEntity<?> deletarEmpresa(Long id);

}
