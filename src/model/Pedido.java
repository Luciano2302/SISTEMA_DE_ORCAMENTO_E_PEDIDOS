package model;

import model.enums.StatusPedido;
import java.time.LocalDateTime;

public class Pedido extends AbstractStatusEntity < StatusPedido > {
    private static int contador = 1;
    private int numero;
    private LocalDateTime data;
    private Orcamento orcamento;

    public Pedido(Orcamento orcamento) {
        super(StatusPedido.ABERTO);
        this.numero = contador++;
        this.data = LocalDateTime.now();
        this.orcamento = orcamento;
    }

    public double getValorTotal() {
        return orcamento.calcularValorTotal();
    }

    public void confirmar() {
        changeStatus(StatusPedido.CONFIRMADO);
    }

    public void faturar() {
        changeStatus(StatusPedido.FATURADO);
    }

    public void entregar() {
        changeStatus(StatusPedido.ENTREGUE);
    }

    public void cancelar() {
        changeStatus(StatusPedido.CANCELADO);
    }

    public int getNumero() {
        return numero;
    }
    public LocalDateTime getData() {
        return data;
    }
    public Orcamento getOrcamento() {
        return orcamento;
    }
    public StatusPedido getStatus() {
        return getCurrentStatus();
    }
}