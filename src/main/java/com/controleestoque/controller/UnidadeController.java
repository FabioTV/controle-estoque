package com.controleestoque.controller;

import java.util.List;

import javax.validation.Valid;

import com.controleestoque.dto.UnidadeDTO;
import com.controleestoque.service.UnidadeService;

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
@RequestMapping(value="/unidade")
public class UnidadeController {
    
    @Autowired
    UnidadeService unidadeService;

    @GetMapping
    public List<UnidadeDTO> listarUnidades(){
        return unidadeService.listarUnidades();
    }

    @GetMapping("/{id}")
    public UnidadeDTO listerUnidade(@Valid @PathVariable(value = "id") Long id){
        return unidadeService.listarUnidade(id);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUnidade(@Valid @RequestBody UnidadeDTO unidadeDTO){
        return unidadeService.cadastrarUnidade(unidadeDTO);
    }

    @PutMapping
    public ResponseEntity<?> editarUnidade(@Valid @RequestBody UnidadeDTO unidadeDTO){
        return  unidadeService.editarUnidade(unidadeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUnidade(@PathVariable(value = "id") Long id){
        return unidadeService.deletarUnidade(id);
    }
}
