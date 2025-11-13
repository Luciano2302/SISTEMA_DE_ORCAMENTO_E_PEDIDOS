package model;

import model.enums.StatusFatura;
import java.time.LocalDateTime;

public class Fatura extends AbstractStatusEntity < StatusFatura > {
    private static int contador = 1;
    private int numero;
    private LocalDateTime data;
    private double valorTotal;
    private Pedido pedido;

    public Fatura(Pedido pedido) {
        super(StatusFatura.ABERTA);
        this.numero = contador++;
        this.data = LocalDateTime.now();
        this.pedido = pedido;
        this.valorTotal = pedido.getValorTotal();
    }

    public void pagar() {
        changeStatus(StatusFatura.PAGA);
    }

    public void cancelar() {
        changeStatus(StatusFatura.CANCELADA);
    }

    public void marcarComoAtrasada() {
        changeStatus(StatusFatura.ATRASADA);
    }

    public int getNumero() {
        return numero;
    }
    public LocalDateTime getData() {
        return data;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public StatusFatura getStatus() {
        return getCurrentStatus();
    }
}