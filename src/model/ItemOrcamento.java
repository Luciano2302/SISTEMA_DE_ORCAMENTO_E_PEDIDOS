package model;

public class ItemOrcamento {
    private ItemVendavel item;
    private int quantidade;
    private double precoUnitario;
    
    public ItemOrcamento(ItemVendavel item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
        this.precoUnitario = item.getPreco();
    }
    
    public double getValorTotal() {
        return quantidade * precoUnitario;
    }
    
    // Getters
    public ItemVendavel getItem() { return item; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }
}