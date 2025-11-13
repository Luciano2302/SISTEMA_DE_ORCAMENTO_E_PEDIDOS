package menu.impl;

import menu.MenuOption;
import usecase.impl.CadastrarProdutoUseCase;
import java.util.Scanner;

public class CadastrarProdutoMenuOption implements MenuOption {
    
    private final CadastrarProdutoUseCase useCase;
    private final Scanner scanner;
    
    public CadastrarProdutoMenuOption(CadastrarProdutoUseCase useCase, Scanner scanner) {
        this.useCase = useCase;
        this.scanner = scanner;
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== CADASTRAR PRODUTO ===");
        
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        
        System.out.print("Preço: R$ ");
        double preco = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Estoque: ");
        int estoque = Integer.parseInt(scanner.nextLine());
        
        try {
            var input = new CadastrarProdutoUseCase.Input(codigo, descricao, preco, estoque);
            var produto = useCase.execute(input);
            System.out.println("✅ Produto cadastrado: " + produto.getDescricao());
        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar produto: " + e.getMessage());
        }
    }
    
    @Override
    public String getDescription() {
        return "Cadastrar Produto";
    }
    
    @Override
    public int getOrder() {
        return 1;
    }
}