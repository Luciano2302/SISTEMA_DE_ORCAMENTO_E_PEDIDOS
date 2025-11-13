package model;

import model.enums.StatusOrcamento;
import java.util.ArrayList;
import java.util.List;

public class Orcamento extends AbstractStatusEntity<StatusOrcamento> {
    private static int contador = 1;
    private int numero;
    private Cliente cliente;
    private List<ItemOrcamento> itens;

    public Orcamento(Cliente cliente) {
        super(StatusOrcamento.RASCUNHO);
        this.numero = contador++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemVendavel item, int quantidade) {
        if (!item.isDisponivel(quantidade)) {
            throw new IllegalArgumentException("Item não disponível na quantidade solicitada");
        }
        itens.add(new ItemOrcamento(item, quantidade));
        setUpdatedAt(java.time.LocalDateTime.now());
    }

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(ItemOrcamento::getValorTotal).sum();
    }

    public void enviarParaAprovacao() {
        changeStatus(StatusOrcamento.AGUARDANDO_APROVACAO);
    }

    public void aprovar() {
        changeStatus(StatusOrcamento.APROVADO);
    }

    public void rejeitar() {
        changeStatus(StatusOrcamento.REJEITADO);
    }

    public void cancelar() {
        changeStatus(StatusOrcamento.CANCELADO);
    }

    public int getNumero() { return numero; }
    public Cliente getCliente() { return cliente; }
    public List<ItemOrcamento> getItens() { return itens; }
    public StatusOrcamento getStatus() { return getCurrentStatus(); }
}