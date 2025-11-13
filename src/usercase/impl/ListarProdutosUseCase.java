package usecase.impl;

import model.Produto;
import repository.ProdutoRepository;
import usecase.UseCase;
import java.util.List;

public class ListarProdutosUseCase implements UseCase<Void, List<Produto>> {
    
    private final ProdutoRepository produtoRepository;
    
    public ListarProdutosUseCase(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    
    @Override
    public List<Produto> execute(Void input) {
        return produtoRepository.listarTodos();
    }
}