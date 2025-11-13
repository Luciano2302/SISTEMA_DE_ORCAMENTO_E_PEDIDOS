package usecase.impl;

import model.Pedido;
import repository.PedidoRepository;
import usecase.UseCase;
import java.util.List;

public class ListarPedidosUseCase implements UseCase<Void, List<Pedido>> {
    
    private final PedidoRepository pedidoRepository;
    
    public ListarPedidosUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
    
    @Override
    public List<Pedido> execute(Void input) {
        return pedidoRepository.listarTodos();
    }
}