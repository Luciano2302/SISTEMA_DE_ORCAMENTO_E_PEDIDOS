package menu.impl;

import menu.MenuOption;
import usecase.impl.CadastrarClienteUseCase;
import java.util.Scanner;

public class CadastrarClienteMenuOption implements MenuOption {
    
    private final CadastrarClienteUseCase useCase;
    private final Scanner scanner;
    
    public CadastrarClienteMenuOption(CadastrarClienteUseCase useCase, Scanner scanner) {
        this.useCase = useCase;
        this.scanner = scanner;
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== CADASTRAR CLIENTE ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        try {
            var input = new CadastrarClienteUseCase.Input(nome, email, telefone);
            var cliente = useCase.execute(input);
            System.out.println("✅ Cliente cadastrado: " + cliente.getNome());
        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
    
    @Override
    public String getDescription() {
        return "Cadastrar Cliente";
    }
    
    @Override
    public int getOrder() {
        return 3;
    }
}