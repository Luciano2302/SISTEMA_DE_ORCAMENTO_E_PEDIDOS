package menu.impl;

import menu.MenuOption;
import usecase.impl.CadastrarServicoUseCase;
import java.util.Scanner;

public class CadastrarServicoMenuOption implements MenuOption {
    
    private final CadastrarServicoUseCase useCase;
    private final Scanner scanner;
    
    public CadastrarServicoMenuOption(CadastrarServicoUseCase useCase, Scanner scanner) {
        this.useCase = useCase;
        this.scanner = scanner;
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== CADASTRAR SERVIÇO ===");
        
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        
        System.out.print("Preço: R$ ");
        double preco = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Duração (horas): ");
        int duracao = Integer.parseInt(scanner.nextLine());
        
        try {
            var input = new CadastrarServicoUseCase.Input(codigo, descricao, preco, duracao);
            var servico = useCase.execute(input);
            System.out.println("✅ Serviço cadastrado: " + servico.getDescricao());
        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar serviço: " + e.getMessage());
        }
    }
    
    @Override
    public String getDescription() {
        return "Cadastrar Serviço";
    }
    
    @Override
    public int getOrder() {
        return 2;
    }
}