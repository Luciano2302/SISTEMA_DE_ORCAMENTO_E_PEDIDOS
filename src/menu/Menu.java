package menu;

public interface Menu {
    void exibir();
    void processarOpcao(int opcao);
    boolean isExecutando();
    void sair();
    void executar();
}