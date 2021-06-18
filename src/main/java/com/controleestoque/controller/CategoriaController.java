package com.controleestoque.controller;

import java.util.List;

import javax.validation.Valid;

import com.controleestoque.dto.CategoriaDTO;
import com.controleestoque.service.CategoriaService;

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
@RequestMapping(value="categoria")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> listarCategorias(){
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public CategoriaDTO listerCategoria(@Valid @PathVariable(value = "id") Long id){
        return categoriaService.listarCategoria(id);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO){
        return categoriaService.cadastrarCategoria(categoriaDTO);
    }

    @PutMapping
    public ResponseEntity<?> editarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO){
        return  categoriaService.editarCategoria(categoriaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCategoria(@PathVariable(value = "id") Long id){
        return categoriaService.deletarCategoria(id);
    }
}
