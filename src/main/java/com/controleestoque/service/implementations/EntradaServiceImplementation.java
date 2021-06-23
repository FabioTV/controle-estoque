package com.controleestoque.service.implementations;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.controleestoque.dto.EntradaDTO;
import com.controleestoque.exception.ApiRequestException;
import com.controleestoque.model.Entrada;
import com.controleestoque.repository.EmpresaRepository;
import com.controleestoque.repository.EntradaRepository;
import com.controleestoque.repository.FornecedorRepository;
import com.controleestoque.repository.MovimentoEntradaProdutoRepository;
import com.controleestoque.repository.ProdutoRepository;
import com.controleestoque.service.EntradaService;
import com.controleestoque.service.MovimentoEntradaProdutoService;
import com.controleestoque.service.NotaFiscalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class EntradaServiceImplementation implements EntradaService{

    @Autowired
    EntradaRepository entradaRepository;

    @Autowired
    MovimentoEntradaProdutoRepository movimentoEntradaProdutoRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    FornecedorRepository fornecedorRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    NotaFiscalService notaFiscalService;

    @Autowired 
    MovimentoEntradaProdutoService movimentoEntradaProdutoService;

    @Override
    public List<EntradaDTO> listarEntradas() {
        List<EntradaDTO> entradaDTOs = new ArrayList<>();
        entradaRepository.findAll().forEach(entrada -> {
            EntradaDTO builDto = EntradaDTO.builder()
                    .id(entrada.getId())
                    .createdAt(entrada.getCreatedAt())
                    .notaFiscal(entrada.getNotaFiscal())
                    .empresa(entrada.getEmpresa())
                    .fornecedor(entrada.getFornecedor())
                    .movimentos(entrada.getMovimentos())
                    .build();
            entradaDTOs.add(builDto);
        });
        return entradaDTOs;
    }

    @Override
    public EntradaDTO listarEntrada(Long id) {
        if(entradaRepository.existsById(id)){
            Entrada entrada = entradaRepository.findById(id).get();
            EntradaDTO buildDto = EntradaDTO.builder()
                    .id(entrada.getId())
                    .createdAt(entrada.getCreatedAt())
                    .notaFiscal(entrada.getNotaFiscal())
                    .empresa(entrada.getEmpresa())
                    .fornecedor(entrada.getFornecedor())
                    .movimentos(entrada.getMovimentos())
                    .build();
            return buildDto;
        }
        throw new ApiRequestException("Entrada inexistente!");
    }

    @Override
    public ResponseEntity<?> cadastrarEntrada(EntradaDTO entradaDTO) {
        this.existsEmpresa(entradaDTO.getEmpresa().getId());
        this.existsFornecedor(entradaDTO.getFornecedor().getId());
        Entrada entradaBuilder =  Entrada.builder()
                .createdAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))
                .empresa(entradaDTO.getEmpresa())
                .fornecedor(entradaDTO.getFornecedor())
                .build();
        Entrada entrada = entradaRepository.save(entradaBuilder);
        entradaDTO.getMovimentos().forEach(movimentoEntradaProduto ->{
            movimentoEntradaProduto.setEntrada(entrada);
            movimentoEntradaProdutoService.cadastrarMovimentoEntradaProduto(movimentoEntradaProduto);
        });
        return ResponseEntity.status(HttpStatus.OK).body("Entrada cadastrada com sucesso!");
    }

    @Override
    public ResponseEntity<?> editarEntrada(EntradaDTO entradaDTO) {
        if(entradaRepository.existsById(entradaDTO.getId())){
            this.existsEmpresa(entradaDTO.getEmpresa().getId());
            this.existsFornecedor(entradaDTO.getFornecedor().getId());
            Entrada entradaBuilder =  Entrada.builder()
                    .id(entradaDTO.getId())
                    .createdAt(entradaRepository.findById(entradaDTO.getId()).get().getCreatedAt())
                    .notaFiscal(entradaRepository.findById(entradaDTO.getId()).get().getNotaFiscal())
                    .empresa(entradaDTO.getEmpresa())
                    .fornecedor(entradaDTO.getFornecedor())
                    .build();
            entradaRepository.save(entradaBuilder);
            movimentoEntradaProdutoRepository.findByEntrada(entradaBuilder).forEach(movimento->{
                if(!entradaDTO.getMovimentos().contains(movimento)){
                    movimentoEntradaProdutoService.deletarMovimentoEntradaProduto(movimento.getId());
                }
            });
            entradaDTO.getMovimentos().forEach(movimentoEntradaProduto ->{
                movimentoEntradaProduto.setEntrada(entradaBuilder);
                if(movimentoEntradaProduto.getId() != null){
                    movimentoEntradaProdutoService.editarMovimentoEntradaProduto(movimentoEntradaProduto);
                }else{
                    movimentoEntradaProdutoService.cadastrarMovimentoEntradaProduto(movimentoEntradaProduto);
                }
                    
            });
            return ResponseEntity.status(HttpStatus.OK).body("Entrada editada com sucesso!");
        }
        throw new ApiRequestException("Entrada inexistente!");
    }

    @Override
    public ResponseEntity<?> deletarEntrada(Long id) {
        if(entradaRepository.existsById(id)){
            notaFiscalService.deleteNotaFiscal(id);
            entradaRepository.deleteById(id); 
            return ResponseEntity.status(HttpStatus.OK).body("Entrada deletada com sucesso");
        }
        throw new ApiRequestException("Entrada inexistente!");
    }

    //private methods
    private void existsEmpresa(Long id){
        if(!empresaRepository.existsById(id)){
            throw new ApiRequestException("Empresa inexistente!");
        }
    }

    private void existsFornecedor(Long id){
        if(!fornecedorRepository.existsById(id)){
            throw new ApiRequestException("Fornecedor inexistente!");
        }
    }
}
