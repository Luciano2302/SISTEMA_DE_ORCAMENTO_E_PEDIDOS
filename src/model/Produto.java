package model;

public class Produto extends ItemVendavel {
    private int estoque;

    public Produto(String codigo, String descricao, double preco, int estoque) {
        super(codigo, descricao, preco);
        this.estoque = estoque;
    }

    @Override
    public boolean isDisponivel(int quantidade) {
        return estoque >= quantidade;
    }

    public void reduzirEstoque(int quantidade) {
        if (quantidade <= estoque) {
            estoque -= quantidade;
        } else {
            throw new IllegalArgumentException("Estoque insuficiente");
        }
    }

    public void adicionarEstoque(int quantidade) {
        estoque += quantidade;
    }

    public int getEstoque() {
        return estoque;
    }
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return String.format("%s | Estoque: %d", super.toString(), estoque);
    }
}