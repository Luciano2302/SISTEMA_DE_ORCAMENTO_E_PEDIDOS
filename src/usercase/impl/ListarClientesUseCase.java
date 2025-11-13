package usecase.impl;

import model.Cliente;
import repository.ClienteRepository;
import usecase.UseCase;
import java.util.List;

public class ListarClientesUseCase implements UseCase<Void, List<Cliente>> {
    
    private final ClienteRepository clienteRepository;
    
    public ListarClientesUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    @Override
    public List<Cliente> execute(Void input) {
        return clienteRepository.listarTodos();
    }
}