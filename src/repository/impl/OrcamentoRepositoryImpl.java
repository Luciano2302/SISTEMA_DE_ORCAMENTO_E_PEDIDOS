package repository.impl;

import model.Orcamento;
import repository.OrcamentoRepository;
import java.util.*;
import java.util.List;


public class OrcamentoRepositoryImpl implements OrcamentoRepository {
    private final Map<String, Orcamento> orcamentos = new HashMap<>();

    @Override
    public Orcamento salvar(Orcamento orcamento) {
        orcamentos.put(orcamento.getId().toString(), orcamento);
        return orcamento;
    }

    @Override
    public Optional<Orcamento> buscarPorId(String id) {
        return Optional.ofNullable(orcamentos.get(id));
    }

    @Override
    public List<Orcamento> listarTodos() {
        return new ArrayList<>(orcamentos.values());
    }

    @Override
    public void remover(String id) {
        orcamentos.remove(id);
    }

    @Override
    public Optional<Orcamento> buscarPorNumero(int numero) {
        return orcamentos.values().stream()
            .filter(o -> o.getNumero() == numero)
            .findFirst();
    }

    @Override
    public List<Orcamento> buscarPorCliente(String clienteId) {
        return orcamentos.values().stream()
            .filter(o -> o.getCliente().getId().toString().equals(clienteId))
            .toList();
    }
}