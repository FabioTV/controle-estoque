package com.controleestoque.service.implementations;

import java.util.ArrayList;
import java.util.List;

import com.controleestoque.dto.CategoriaDTO;
import com.controleestoque.exception.ApiRequestException;
import com.controleestoque.model.Categoria;
import com.controleestoque.repository.CategoriaRepository;
import com.controleestoque.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImplementation implements CategoriaService{

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDTO> listarCategorias() {
        List<CategoriaDTO> categoriaDTOs = new ArrayList<>();
        categoriaRepository.findAll().forEach(categoria -> {
            CategoriaDTO builderDto = CategoriaDTO.builder()
                    .id(categoria.getId())
                    .nome(categoria.getNome())
                    .build();
            categoriaDTOs.add(builderDto);
        });
        return categoriaDTOs;
    }

    @Override
    public CategoriaDTO listarCategoria(Long id) {
        if(categoriaRepository.existsById(id)){
            Categoria categoria = categoriaRepository.findById(id).get();
            CategoriaDTO builderDto = CategoriaDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .produtos(categoria.getProdutos())
                .build();
            return builderDto;
        }
        throw new ApiRequestException("Categoria não existe!");
    }

    @Override
    public ResponseEntity<?> cadastrarCategoria(CategoriaDTO categoriaDTO) {
        if(!categoriaRepository.existsByNome(categoriaDTO.getNome().toUpperCase())){
            Categoria categoria = Categoria.builder()
                    .nome(categoriaDTO.getNome().toUpperCase())
                    .build();
            categoriaRepository.save(categoria);
            return ResponseEntity.status(HttpStatus.OK).body("Cadastrada com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Categoria já cadastrada!");
    }

    @Override
    public ResponseEntity<?> editarCategoria(CategoriaDTO categoriaDTO) {
        if(categoriaRepository.existsById(categoriaDTO.getId())){
            this.nomeJaCadastrado(categoriaDTO.getId(), categoriaDTO.getNome());
            Categoria categoria = Categoria.builder()
                    .id(categoriaDTO.getId())
                    .nome(categoriaDTO.getNome().toUpperCase())
                    .build();
            categoriaRepository.save(categoria);
            return ResponseEntity.status(HttpStatus.OK).body("Editada com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Categoria não existe!");
    }

    @Override
    public ResponseEntity<?> deletarCategoria(Long id) {
        if(categoriaRepository.existsById(id)){
            categoriaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Categoria deletada");
        }
        throw new ApiRequestException("Categoria não existe!");
    }

    //private methods
    private void nomeJaCadastrado(Long id, String nome){
        if(categoriaRepository.existsByNome(nome)){
            Categoria marcaRequest = categoriaRepository.findById(id).get();
            Categoria marcaNome = categoriaRepository.findByNome(nome);
            if(marcaRequest.getId() != marcaNome.getId()){
                throw new ApiRequestException("Marca já utilizada!");
            }
        }
    }
}
