package usecase.impl;

import model.Cliente;
import repository.ClienteRepository;
import usecase.UseCase;

public class CadastrarClienteUseCase implements UseCase<CadastrarClienteUseCase.Input, Cliente> {
    
    private final ClienteRepository clienteRepository;
    
    public CadastrarClienteUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    @Override
    public Cliente execute(Input input) {
        Cliente cliente = new Cliente(input.nome(), input.email(), input.telefone());
        return clienteRepository.salvar(cliente);
    }
    
    public record Input(String nome, String email, String telefone) {}
}