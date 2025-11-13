package service;

import model.Fatura;
import model.Pedido;
import model.NotaFiscal;
import model.enums.StatusPedido;
import model.enums.StatusFatura;

public class FaturaService {

    public Fatura criarFatura(Pedido pedido) {
        if (pedido.getStatus() == StatusPedido.CONFIRMADO) {
            Fatura fatura = new Fatura(pedido);
            pedido.faturar();
            return fatura;
        }
        throw new IllegalStateException("Pedido não está confirmado");
    }

    public NotaFiscal emitirNotaFiscal(Fatura fatura) {
        if (fatura.getStatus() == StatusFatura.PAGA) { 
            return new NotaFiscal(fatura);
        }
        throw new IllegalStateException("Fatura não está paga");
    }

    public void pagarFatura(Fatura fatura) {
        fatura.pagar();
    }
}