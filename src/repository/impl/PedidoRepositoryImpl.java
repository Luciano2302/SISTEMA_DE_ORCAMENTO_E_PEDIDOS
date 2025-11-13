package repository.impl;

import model.Pedido;
import repository.PedidoRepository;
import java.util.*;
import java.util.List;


public class PedidoRepositoryImpl implements PedidoRepository {
    private final Map<String, Pedido> pedidos = new HashMap<>();

    @Override
    public Pedido salvar(Pedido pedido) {
        pedidos.put(pedido.getId().toString(), pedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> buscarPorId(String id) {
        return Optional.ofNullable(pedidos.get(id));
    }

    @Override
    public List<Pedido> listarTodos() {
        return new ArrayList<>(pedidos.values());
    }

    @Override
    public void remover(String id) {
        pedidos.remove(id);
    }

    @Override
    public Optional<Pedido> buscarPorNumero(int numero) {
        return pedidos.values().stream()
            .filter(p -> p.getNumero() == numero)
            .findFirst();
    }

    @Override
    public List<Pedido> buscarPorOrcamento(int orcamentoNumero) {
        return pedidos.values().stream()
            .filter(p -> p.getOrcamento().getNumero() == orcamentoNumero)
            .toList();
    }
}