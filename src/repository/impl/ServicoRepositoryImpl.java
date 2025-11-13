package repository.impl;

import model.Servico;
import repository.ServicoRepository;
import java.util.*;
import java.util.List;


public class ServicoRepositoryImpl implements ServicoRepository {
    private final Map<String, Servico> servicos = new HashMap<>();

    @Override
    public Servico salvar(Servico servico) {
        servicos.put(servico.getId().toString(), servico);
        return servico;
    }

    @Override
    public Optional<Servico> buscarPorId(String id) {
        return Optional.ofNullable(servicos.get(id));
    }

    @Override
    public List<Servico> listarTodos() {
        return new ArrayList<>(servicos.values());
    }

    @Override
    public void remover(String id) {
        servicos.remove(id);
    }

    @Override
    public Optional<Servico> buscarPorCodigo(String codigo) {
        return servicos.values().stream()
            .filter(s -> s.getCodigo().equalsIgnoreCase(codigo))
            .findFirst();
    }

    @Override
    public List<Servico> buscarPorDescricao(String descricao) {
        return servicos.values().stream()
            .filter(s -> s.getDescricao().toLowerCase().contains(descricao.toLowerCase()))
            .toList();
    }
}