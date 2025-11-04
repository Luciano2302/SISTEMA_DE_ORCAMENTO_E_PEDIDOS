package model;

import java.util.Date;

public class NotaFiscal {
    private static int contador = 1;

    private int numero;
    private Date data;
    private double valorTotal;
    private Fatura fatura;

    public NotaFiscal(Fatura fatura) {
        this.numero = contador++;
        this.data = new Date();
        this.fatura = fatura;
        this.valorTotal = fatura.getValorTotal();
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
    public Fatura getFatura() {
        return fatura;
    }
}