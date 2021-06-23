package com.controleestoque.controller;

import java.util.List;

import javax.validation.Valid;

import com.controleestoque.dto.FornecedorDTO;
import com.controleestoque.service.FornecedorService;

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
@RequestMapping(value="/fornecedor")
public class FornecedorController {
    
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public List<FornecedorDTO> listarFornecedores() {
        return fornecedorService.listarFornecedores();
    }

    @GetMapping("/{id}")
    public FornecedorDTO listarFornecedor(@PathVariable(value = "id") Long id){
        return fornecedorService.listarFornecedor(id);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarFornecedor(@Valid @RequestBody FornecedorDTO fornecedorDTO){
        return fornecedorService.cadastrarFornecedor(fornecedorDTO);
    }

    @PutMapping
    public ResponseEntity<?> editarFornecedor(@Valid @RequestBody FornecedorDTO fornecedorDTO){
        return fornecedorService.editarFornecedor(fornecedorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarFornecedor(@PathVariable(value = "id") Long id){
        return fornecedorService.deletarFornecedor(id);
    }
}
