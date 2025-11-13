package repository;

import model.Produto;
import java.util.Optional;
import java.util.List;

public interface ProdutoRepository extends Repository<Produto, String> {
    Optional<Produto> buscarPorCodigo(String codigo);
    List<Produto> buscarPorDescricao(String descricao);
}