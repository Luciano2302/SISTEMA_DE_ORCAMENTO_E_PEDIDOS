package model;

import model.enums.StatusPedido;
import java.util.Date;

public class Pedido {
    private static int contador = 1;

    private int numero;
    private Date data;
    private Orcamento orcamento;
    private StatusPedido status;

    public Pedido(Orcamento orcamento) {
        this.numero = contador++;
        this.data = new Date();
        this.orcamento = orcamento;
        this.status = StatusPedido.ABERTO;
    }

    public double getValorTotal() {
        return orcamento.calcularValorTotal();
    }

    public void confirmar() {
        this.status = StatusPedido.CONFIRMADO;
    }

    public void faturar() {
        this.status = StatusPedido.FATURADO;
    }

    public int getNumero() {
        return numero;
    }
    public Date getData() {
        return data;
    }
    public Orcamento getOrcamento() {
        return orcamento;
    }
    public StatusPedido getStatus() {
        return status;
    }
}