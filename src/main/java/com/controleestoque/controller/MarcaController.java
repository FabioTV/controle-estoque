package com.controleestoque.controller;

import java.util.List;

import javax.validation.Valid;

import com.controleestoque.dto.MarcaDTO;
import com.controleestoque.service.MarcaService;

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
@RequestMapping(value = "/marca")
public class MarcaController {

    @Autowired
    MarcaService marcaService;
    
    @GetMapping
    public List<MarcaDTO> listarMarcas(){
        return marcaService.listarMarcas();
    }

    @GetMapping("/{id}")
    public MarcaDTO listarMarca(@Valid @PathVariable(value = "id") Long id){
        return marcaService.listarMarca(id);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarMarca(@Valid @RequestBody MarcaDTO marcaDTO){
        return marcaService.cadastrarMarca(marcaDTO);
    }

    @PutMapping
    public ResponseEntity<?> editarMarca(@Valid @RequestBody MarcaDTO marcaDTO){
        return marcaService.editarMarca(marcaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarMarca(@PathVariable(value = "id") Long id){
        return marcaService.deletarMarca(id);
    }
}
