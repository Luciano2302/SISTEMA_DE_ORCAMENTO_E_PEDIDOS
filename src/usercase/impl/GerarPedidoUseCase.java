package usecase.impl;

import model.Orcamento;
import model.Pedido;
import repository.PedidoRepository;
import usecase.UseCase;

public class GerarPedidoUseCase implements UseCase<Orcamento, Pedido> {
    
    private final PedidoRepository pedidoRepository;
    
    public GerarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
    
    @Override
    public Pedido execute(Orcamento orcamento) {
        if (!orcamento.getStatus().name().equals("APROVADO")) {
            throw new IllegalStateException("Orçamento não está aprovado");
        }
        Pedido pedido = new Pedido(orcamento);
        return pedidoRepository.salvar(pedido);
    }
}