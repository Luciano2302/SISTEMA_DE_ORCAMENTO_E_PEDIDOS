package menu.impl;

import menu.MenuOption;
import usecase.impl.ListarServicosUseCase;

public class ListarServicosMenuOption implements MenuOption {
    
    private final ListarServicosUseCase useCase;
    
    public ListarServicosMenuOption(ListarServicosUseCase useCase) {
        this.useCase = useCase;
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== LISTA DE SERVIÇOS ===");
        var servicos = useCase.execute(null);
        
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
            return;
        }
        
        for (int i = 0; i < servicos.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, servicos.get(i));
        }
    }
    
    @Override
    public String getDescription() {
        return "Listar Serviços";
    }
    
    @Override
    public int getOrder() {
        return 5;
    }
}