package model;

public class Servico extends ItemVendavel {
    private int duracaoHoras;

    public Servico(String codigo, String descricao, double preco, int duracaoHoras) {
        super(codigo, descricao, preco);
        this.duracaoHoras = duracaoHoras;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }
    public void setDuracaoHoras(int duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }
}