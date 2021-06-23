package com.controleestoque.service.implementations;

import java.math.BigDecimal;

import com.controleestoque.exception.ApiRequestException;
import com.controleestoque.model.MovimentoEntradaProduto;
import com.controleestoque.repository.MovimentoEntradaProdutoRepository;
import com.controleestoque.repository.ProdutoRepository;
import com.controleestoque.service.MovimentoEntradaProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentoEntradaProdutoServiceImplementation implements MovimentoEntradaProdutoService{

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    MovimentoEntradaProdutoRepository movimentoEntradaProdutoRepository;

    @Override
    public void cadastrarMovimentoEntradaProduto(MovimentoEntradaProduto movimentoEntradaProduto) {
        this.existsProduto(movimentoEntradaProduto.getProduto().getId());
        MovimentoEntradaProduto movimentoBuilder = MovimentoEntradaProduto.builder()
                    .entrada(movimentoEntradaProduto.getEntrada())
                    .produto(movimentoEntradaProduto.getProduto())
                    .quantidade(movimentoEntradaProduto.getQuantidade())
                    .precoUnitario(movimentoEntradaProduto.getPrecoUnitario())
                    .precoTotal(movimentoEntradaProduto.getPrecoUnitario()
                            .multiply(BigDecimal.valueOf(movimentoEntradaProduto.getQuantidade())))
                    .build();

            movimentoEntradaProdutoRepository.save(movimentoBuilder);
    }

    @Override
    public void editarMovimentoEntradaProduto(MovimentoEntradaProduto movimentoEntradaProduto){
        this.existsMovimento(movimentoEntradaProduto.getId());
        this.existsProduto(movimentoEntradaProduto.getProduto().getId());
        MovimentoEntradaProduto movimentoBuilder = MovimentoEntradaProduto.builder()
                    .id(movimentoEntradaProduto.getId())
                    .entrada(movimentoEntradaProduto.getEntrada())
                    .produto(movimentoEntradaProduto.getProduto())
                    .quantidade(movimentoEntradaProduto.getQuantidade())
                    .precoUnitario(movimentoEntradaProduto.getPrecoUnitario())
                    .precoTotal(movimentoEntradaProduto.getPrecoUnitario()
                            .multiply(BigDecimal.valueOf(movimentoEntradaProduto.getQuantidade())))
                    .build();

            movimentoEntradaProdutoRepository.save(movimentoBuilder);
    }

    @Override
    public void deletarMovimentoEntradaProduto(Long id) {
        if(!movimentoEntradaProdutoRepository.existsById(id)){
            throw new ApiRequestException("Produto de entrada inexistente!");
        }
        movimentoEntradaProdutoRepository.deleteById(id);
    }
    
    //Private mathods
    private void existsProduto(Long id){
        if(!produtoRepository.existsById(id)){
            throw new ApiRequestException("Produto inexistente!");
        }
    }

    private void existsMovimento(Long id){
        if(!movimentoEntradaProdutoRepository.existsById(id)){
            throw new ApiRequestException("Movimento inexistente!");
        }
    }
}
