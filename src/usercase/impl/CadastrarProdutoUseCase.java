package usecase.impl;

import model.Produto;
import repository.ProdutoRepository;
import usecase.UseCase;

public class CadastrarProdutoUseCase implements UseCase<CadastrarProdutoUseCase.Input, Produto> {
    
    private final ProdutoRepository produtoRepository;
    
    public CadastrarProdutoUseCase(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    
    @Override
    public Produto execute(Input input) {
        Produto produto = new Produto(input.codigo(), input.descricao(), input.preco(), input.estoque());
        return produtoRepository.salvar(produto);
    }
    
    public record Input(String codigo, String descricao, double preco, int estoque) {}
}