package model;

import java.time.LocalDateTime;

public class NotaFiscal extends BaseEntity {
    private static int contador = 1;
    private int numero;
    private LocalDateTime data;
    private double valorTotal;
    private Fatura fatura;

    public NotaFiscal(Fatura fatura) {
        super();
        this.numero = contador++;
        this.data = LocalDateTime.now();
        this.fatura = fatura;
        this.valorTotal = fatura.getValorTotal();
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
    public Fatura getFatura() {
        return fatura;
    }
}