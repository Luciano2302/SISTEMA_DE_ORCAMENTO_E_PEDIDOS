package repository.impl;

import model.Fatura;
import repository.FaturaRepository;
import java.util.*;

public class FaturaRepositoryImpl implements FaturaRepository {
    private final Map<String, Fatura> faturas = new HashMap<>();

    @Override
    public Fatura salvar(Fatura fatura) {
        faturas.put(fatura.getId().toString(), fatura);
        return fatura;
    }

    @Override
    public Optional<Fatura> buscarPorId(String id) {
        return Optional.ofNullable(faturas.get(id));
    }

    @Override
    public List<Fatura> listarTodos() {
        return new ArrayList<>(faturas.values());
    }

    @Override
    public void remover(String id) {
        faturas.remove(id);
    }

    @Override
    public Optional<Fatura> buscarPorNumero(int numero) {
        return faturas.values().stream()
            .filter(f -> f.getNumero() == numero)
            .findFirst();
    }

    @Override
    public Optional<Fatura> buscarPorPedido(int pedidoNumero) {
        return faturas.values().stream()
            .filter(f -> f.getPedido().getNumero() == pedidoNumero)
            .findFirst();
    }
}