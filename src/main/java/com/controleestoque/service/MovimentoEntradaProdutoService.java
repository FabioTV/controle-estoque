package com.controleestoque.service;

import com.controleestoque.model.MovimentoEntradaProduto;

public interface MovimentoEntradaProdutoService {
    
    void cadastrarMovimentoEntradaProduto(MovimentoEntradaProduto movimentoEntradaProduto);
    void deletarMovimentoEntradaProduto(Long id);
    void editarMovimentoEntradaProduto(MovimentoEntradaProduto movimentoEntradaProduto);
}
