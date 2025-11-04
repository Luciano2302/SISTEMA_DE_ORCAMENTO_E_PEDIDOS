package model;

import model.enums.StatusOrcamento;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Orcamento {
    private static int contador = 1;
    
    private int numero;
    private Date data;
    private Cliente cliente;
    private StatusOrcamento status;
    private List<ItemOrcamento> itens;
    
    public Orcamento(Cliente cliente) {
        this.numero = contador++;
        this.data = new Date();
        this.cliente = cliente;
        this.status = StatusOrcamento.RASCUNHO;
        this.itens = new ArrayList<>();
    }
    
    public void adicionarItem(ItemVendavel item, int quantidade) {
        ItemOrcamento itemOrcamento = new ItemOrcamento(item, quantidade);
        itens.add(itemOrcamento);
    }
    
    public double calcularValorTotal() {
        return itens.stream()
                .mapToDouble(ItemOrcamento::getValorTotal)
                .sum();
    }
    
    public void aprovar() {
        if (this.status == StatusOrcamento.AGUARDANDO_APROVACAO) {
            this.status = StatusOrcamento.APROVADO;
        }
    }
    
    public void enviarParaAprovacao() {
        if (this.status == StatusOrcamento.RASCUNHO) {
            this.status = StatusOrcamento.AGUARDANDO_APROVACAO;
        }
    }
    
    public int getNumero() { return numero; }
    public Date getData() { return data; }
    public Cliente getCliente() { return cliente; }
    public StatusOrcamento getStatus() { return status; }
    public List<ItemOrcamento> getItens() { return itens; }
}