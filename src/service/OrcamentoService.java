package service;

import model.*;
import model.enums.StatusOrcamento;

public class OrcamentoService {

    public Orcamento criarOrcamento(Cliente cliente) {
        return new Orcamento(cliente);
    }

    public void adicionarItem(Orcamento orcamento, ItemVendavel item, int quantidade) {
        orcamento.adicionarItem(item, quantidade);
    }

    public double calcularTotal(Orcamento orcamento) {
        return orcamento.calcularValorTotal();
    }

    public void enviarParaAprovacao(Orcamento orcamento) {
        orcamento.enviarParaAprovacao();
    }

    public void aprovarOrcamento(Orcamento orcamento) {
        orcamento.aprovar();
    }

    public boolean podeSerConvertidoEmPedido(Orcamento orcamento) {
        return orcamento.getStatus() == StatusOrcamento.APROVADO;
    }
}