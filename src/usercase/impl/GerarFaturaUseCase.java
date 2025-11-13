package usecase.impl;

import model.Fatura;
import model.Pedido;
import repository.PedidoRepository;
import usecase.UseCase;

public class GerarFaturaUseCase implements UseCase<Pedido, Fatura> {
    
    private final PedidoRepository pedidoRepository;
    
    public GerarFaturaUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }
    
    @Override
    public Fatura execute(Pedido pedido) {
        if (!pedido.getStatus().name().equals("CONFIRMADO")) {
            throw new IllegalStateException("Pedido não está confirmado");
        }
        Fatura fatura = new Fatura(pedido);
        pedido.faturar();
        pedidoRepository.salvar(pedido);
        return fatura;
    }
}