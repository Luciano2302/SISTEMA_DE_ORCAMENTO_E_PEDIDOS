package model;

import model.enums.StatusFatura;
import java.util.Date;

public class Fatura {
    private static int contador = 1;

    private int numero;
    private Date data;
    private double valorTotal;
    private StatusFatura status;
    private Pedido pedido;

    public Fatura(Pedido pedido) {
        this.numero = contador++;
        this.data = new Date();
        this.pedido = pedido;
        this.valorTotal = pedido.getValorTotal();
        this.status = StatusFatura.ABERTA;
    }

    public int getNumero() {
        return numero;
    }
    public Date getData() {
        return data;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public StatusFatura getStatus() {
        return status;
    }
    public Pedido getPedido() {
        return pedido;
    }

    public void setStatus(StatusFatura status) {
        this.status = status;
    }
}