package com.controleestoque.service.implementations;

import java.util.ArrayList;
import java.util.List;

import com.controleestoque.dto.ProdutoDTO;
import com.controleestoque.exception.ApiRequestException;
import com.controleestoque.model.Produto;
import com.controleestoque.repository.CategoriaRepository;
import com.controleestoque.repository.MarcaRepository;
import com.controleestoque.repository.ProdutoRepository;
import com.controleestoque.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImplementation implements ProdutoService{

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    MarcaRepository marcaRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public List<ProdutoDTO> listarProdutos() {
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();
        produtoRepository.findAll().forEach(produto -> {
            ProdutoDTO builderDto = ProdutoDTO.builder()
                    .id(produto.getId())
                    .nome(produto.getNome())
                    .unidadeMedida(produto.getUnidadeMedida())
                    .localArmazenado(produto.getLocalArmazenado())
                    .marca(produto.getMarca())
                    .categoria(produto.getCategoria())
                    .build();
            produtoDTOs.add(builderDto);
        });
        return produtoDTOs;
    }

    @Override
    public List<ProdutoDTO> listarProdutos(String nome) {
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();
        produtoRepository.findByNomeLike("%" + nome + "%").forEach(produto -> {
            ProdutoDTO builderDto = ProdutoDTO.builder()
                    .id(produto.getId())
                    .nome(produto.getNome())
                    .unidadeMedida(produto.getUnidadeMedida())
                    .localArmazenado(produto.getLocalArmazenado())
                    .marca(marcaRepository.findById(produto.getMarca().getId()).get())
                    .categoria(categoriaRepository.findById(produto.getCategoria().getId()).get())
                    .build();
            produtoDTOs.add(builderDto);
        });
        return produtoDTOs;
    }

    @Override
    public ProdutoDTO listarProduto(Long id) {
        if(produtoRepository.existsById(id)){
            Produto produto = produtoRepository.findById(id).get();
            ProdutoDTO builderDto = ProdutoDTO.builder()
                    .id(produto.getId())
                    .nome(produto.getNome())
                    .unidadeMedida(produto.getUnidadeMedida())
                    .localArmazenado(produto.getLocalArmazenado())
                    .marca(marcaRepository.findById(produto.getMarca().getId()).get())
                    .categoria(categoriaRepository.findById(produto.getCategoria().getId()).get())
                    .build();
            return builderDto;
        }
        throw new ApiRequestException("Produto inexistente!");
    }

    @Override
    public ResponseEntity<?> cadastrarProduto(ProdutoDTO produtoDTO) {
        this.existeUmProdutoComNomeMarcaIgual(produtoDTO);
        this.existeMarca(produtoDTO.getMarca().getId());
        this.existeCategoria(produtoDTO.getCategoria().getId());
        Produto produto = Produto.builder()
                .nome(produtoDTO.getNome().toUpperCase())
                .unidadeMedida(produtoDTO.getUnidadeMedida().toUpperCase())
                .localArmazenado(produtoDTO.getLocalArmazenado().toUpperCase())
                .marca(produtoDTO.getMarca())
                .categoria(produtoDTO.getCategoria())
                .build();
        produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.OK).body("Produto cadastrado com sucesso!");
    }

    @Override
    public ResponseEntity<?> editarProduto(ProdutoDTO produtoDTO) {
        if(produtoRepository.existsById(produtoDTO.getId())){
            this.existeUmProdutoComNomeMarcaIgualEIdDiferente(produtoDTO);
            this.existeMarca(produtoDTO.getMarca().getId());
            this.existeCategoria(produtoDTO.getCategoria().getId());
            Produto produto = Produto.builder()
                    .nome(produtoDTO.getNome().toUpperCase())
                    .unidadeMedida(produtoDTO.getUnidadeMedida().toUpperCase())
                    .localArmazenado(produtoDTO.getLocalArmazenado().toUpperCase())
                    .marca(produtoDTO.getMarca())
                    .categoria(produtoDTO.getCategoria())
                    .build();
            produtoRepository.save(produto);
            return ResponseEntity.status(HttpStatus.OK).body("Produto editado com sucesso!");
        }
        throw new ApiRequestException("Produto inexistente!");
    }

    @Override
    public ResponseEntity<?> deletarProduto(Long id) {
        if(produtoRepository.existsById(id)){
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
        }
        throw new ApiRequestException("Produto inexistente!");
    }

    
    //Private mathods
    void existeUmProdutoComNomeMarcaIgual(ProdutoDTO produtoDTO){
        if(produtoRepository.existsByNome(produtoDTO.getNome().toUpperCase())){
            Produto produto = produtoRepository.findByNome(produtoDTO.getNome().toUpperCase());
            if(produto.getMarca().getId() == produtoDTO.getMarca().getId()){
                throw new ApiRequestException("Produto já foi cadastrado!");
            }
        }
    }

    void existeUmProdutoComNomeMarcaIgualEIdDiferente(ProdutoDTO produtoDTO){
        if(produtoRepository.existsByNome(produtoDTO.getNome().toUpperCase())){
            Produto produto = produtoRepository.findByNome(produtoDTO.getNome().toUpperCase());
            if(produto.getMarca().getId() == produtoDTO.getMarca().getId() && produto.getId() != produtoDTO.getId()){
                throw new ApiRequestException("Produto com mesmo nome e marca já cadastrados!");
            }
        }
    }
    
    void existeMarca(Long marcaId){
        if(!marcaRepository.existsById(marcaId)){
            throw new ApiRequestException("Marca a ser vinculada inexistente!");
        }
    }

    void existeCategoria(Long categoriaId){
        if(!categoriaRepository.existsById(categoriaId)){
            throw new ApiRequestException("Categoria a ser vinculada inexistente!");
        }
    }
}
