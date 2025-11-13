package repository.impl;

import model.Produto;
import repository.ProdutoRepository;
import java.util.*;
import java.util.List;

public class ProdutoRepositoryImpl implements ProdutoRepository {
    private final Map<String, Produto> produtos = new HashMap<>();

    @Override
    public Produto salvar(Produto produto) {
        produtos.put(produto.getId().toString(), produto);
        return produto;
    }

    @Override
    public Optional<Produto> buscarPorId(String id) {
        return Optional.ofNullable(produtos.get(id));
    }

    @Override
    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos.values());
    }

    @Override
    public void remover(String id) {
        produtos.remove(id);
    }

    @Override
    public Optional<Produto> buscarPorCodigo(String codigo) {
        return produtos.values().stream()
            .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
            .findFirst();
    }

    @Override
    public List<Produto> buscarPorDescricao(String descricao) {
        return produtos.values().stream()
            .filter(p -> p.getDescricao().toLowerCase().contains(descricao.toLowerCase()))
            .toList();
    }
}