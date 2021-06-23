package com.controleestoque.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.controleestoque.dto.ProdutoDTO;
import com.controleestoque.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
    
    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public List<ProdutoDTO> listarProdutos(@RequestParam Optional<String> nome){
        if(nome.isPresent()){
            return produtoService.listarProdutos(nome.get().toUpperCase());
        }else{
            return produtoService.listarProdutos();
        }
    }

    @GetMapping("/{id}")
    public ProdutoDTO listarProduto(@PathVariable(value = "id") Long id){
        return produtoService.listarProduto(id);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@Valid @RequestBody ProdutoDTO produtoDTO){
        return produtoService.cadastrarProduto(produtoDTO);
    }

    @PutMapping
    public ResponseEntity<?> editarProduto(@Valid @RequestBody ProdutoDTO produtoDTO){
        return produtoService.editarProduto(produtoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable(value = "id") Long id){
        return produtoService.deletarProduto(id);
    }
}
