package menu.impl;

import menu.Menu;
import menu.MenuOption;
import java.util.*;

public class MenuPrincipal implements Menu {
    private final List<MenuOption> options;
    private final Scanner scanner;
    private boolean executando;

    public MenuPrincipal(List<MenuOption> options, Scanner scanner) {
        this.options = new ArrayList<>(options);
        this.scanner = scanner;
        this.executando = true;
        ordenarOpcoes();
    }

    private void ordenarOpcoes() {
        options.sort(Comparator.comparingInt(MenuOption::getOrder));
    }

    @Override
    public void exibir() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, options.get(i).getDescription());
        }
        System.out.println("0 - Sair");
        System.out.println("======================");
    }

    @Override
    public void processarOpcao(int opcao) {
        if (opcao == 0) {
            sair();
            return;
        }

        if (opcao > 0 && opcao <= options.size()) {
            try {
                options.get(opcao - 1).execute();
            } catch (Exception e) {
                System.out.println("❌ Erro ao executar opção: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Opção inválida!");
        }
    }

    @Override
    public boolean isExecutando() {
        return executando;
    }

    @Override
    public void sair() {
        System.out.println("Saindo do sistema...");
        executando = false;
    }

    public void executar() {
        while (isExecutando()) {
            exibir();
            int opcao = lerInteiro("Escolha uma opção: ");
            processarOpcao(opcao);
        }
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
            }
        }
    }
}