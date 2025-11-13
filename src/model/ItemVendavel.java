package model;

public abstract class ItemVendavel extends BaseEntity {
    protected String codigo;
    protected String descricao;
    protected double preco;

    public ItemVendavel(String codigo, String descricao, double preco) {
        super();
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
    }

    public abstract boolean isDisponivel(int quantidade);

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    @Override
    public String toString() {
        return String.format("%s | %s | R$ %.2f", codigo, descricao, preco);
    }
}