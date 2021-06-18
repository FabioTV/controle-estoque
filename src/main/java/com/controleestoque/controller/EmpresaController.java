package com.controleestoque.controller;

import java.util.List;

import javax.validation.Valid;

import com.controleestoque.dto.EmpresaDTO;
import com.controleestoque.service.EmpresaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {
    
    @Autowired
    EmpresaService empresaService;

    @GetMapping
    public List<EmpresaDTO> listarEmpresas(){
        return empresaService.listarEmpresas();
    }

    @GetMapping("/{id}")
    public EmpresaDTO listarEmpresa(@Valid @PathVariable(value = "id") Long id){
        return empresaService.listarEmpresa(id);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarEmpresa(@Valid @RequestBody EmpresaDTO empresaDTO){
        return empresaService.cadastrarEmpresa(empresaDTO);
    }

    @PutMapping
    public ResponseEntity<?> editarEmpresa(@Valid @RequestBody EmpresaDTO empresaDTO){
        return empresaService.editarEmpresa(empresaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEmpresa(@PathVariable(value = "id") Long id){
        return empresaService.deletarEmpresa(id);
    }
}
