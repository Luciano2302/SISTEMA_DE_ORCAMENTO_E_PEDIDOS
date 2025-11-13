package repository.impl;

import model.Cliente;
import repository.ClienteRepository;
import java.util.*;
import java.util.List;


public class ClienteRepositoryImpl implements ClienteRepository {
    private final Map<String, Cliente> clientes = new HashMap<>();

    @Override
    public Cliente salvar(Cliente cliente) {
        clientes.put(cliente.getId().toString(), cliente);
        return cliente;
    }

    @Override
    public Optional<Cliente> buscarPorId(String id) {
        return Optional.ofNullable(clientes.get(id));
    }

    @Override
    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes.values());
    }

    @Override
    public void remover(String id) {
        clientes.remove(id);
    }

    @Override
    public Optional<Cliente> buscarPorEmail(String email) {
        return clientes.values().stream()
            .filter(c -> c.getEmail().equalsIgnoreCase(email))
            .findFirst();
    }

    @Override
    public List<Cliente> buscarPorNome(String nome) {
        return clientes.values().stream()
            .filter(c -> c.getNome().toLowerCase().contains(nome.toLowerCase()))
            .toList();
    }
}