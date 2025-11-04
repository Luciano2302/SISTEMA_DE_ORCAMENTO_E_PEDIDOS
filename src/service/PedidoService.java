package service;

import model.Orcamento;
import model.Pedido;

public class PedidoService {
    
    public Pedido criarPedidoFromOrcamento(Orcamento orcamento) {
        if (orcamento.getStatus() == model.enums.StatusOrcamento.APROVADO) {
            return new Pedido(orcamento);
        }
        throw new IllegalStateException("Orçamento não está aprovado");
    }
    
    public void confirmarPedido(Pedido pedido) {
        pedido.confirmar();
    }
}