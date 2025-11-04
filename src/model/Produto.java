package model;

public class Produto extends ItemVendavel {
    private int estoque;
    
    public Produto(String codigo, String descricao, double preco, int estoque) {
        super(codigo, descricao, preco);
        this.estoque = estoque;
    }
    
    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }
}