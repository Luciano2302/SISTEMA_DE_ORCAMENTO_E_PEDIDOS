package usecase.impl;

import model.Pedido;
import repository.PedidoRepository;
import usecase.VoidUseCase;

public class ConfirmarPedidoUseCase implements VoidUseCase<Pedido> {
    
    private final PedidoRepository pedidoRepository;
    
    public ConfirmarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
    
    @Override
    public void execute(Pedido pedido) {
        pedido.confirmar();
        pedidoRepository.salvar(pedido);
    }
}