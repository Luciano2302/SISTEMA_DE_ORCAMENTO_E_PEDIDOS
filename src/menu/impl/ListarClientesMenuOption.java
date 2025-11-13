package menu.impl;

import menu.MenuOption;
import usecase.impl.ListarClientesUseCase;

public class ListarClientesMenuOption implements MenuOption {
    
    private final ListarClientesUseCase useCase;
    
    public ListarClientesMenuOption(ListarClientesUseCase useCase) {
        this.useCase = useCase;
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== LISTA DE CLIENTES ===");
        var clientes = useCase.execute(null);
        
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        
        for (int i = 0; i < clientes.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, clientes.get(i));
        }
    }
    
    @Override
    public String getDescription() {
        return "Listar Clientes";
    }
    
    @Override
    public int getOrder() {
        return 6;
    }
}