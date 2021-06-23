package com.controleestoque.controller;

import java.util.List;

import javax.validation.Valid;

import com.controleestoque.dto.EntradaDTO;
import com.controleestoque.service.EntradaService;

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
@RequestMapping(value = "/entrada")
public class EntradaController {
    
    @Autowired
    EntradaService entradaService;


    @GetMapping
    public List<EntradaDTO> listarEntradas(){
        return entradaService.listarEntradas();
    }

    @GetMapping("/{id}")
    public EntradaDTO listarEntrada(@PathVariable(value = "id") Long id){
        return entradaService.listarEntrada(id);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarEntrada(@Valid @RequestBody EntradaDTO entradaDTO){
        return entradaService.cadastrarEntrada(entradaDTO);
    }

    @PutMapping
    public ResponseEntity<?> editarEntrada(@Valid @RequestBody EntradaDTO entradaDTO){
        return entradaService.editarEntrada(entradaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEntrada(@PathVariable(value = "id") Long id){
        return entradaService.deletarEntrada(id);
    }
}
