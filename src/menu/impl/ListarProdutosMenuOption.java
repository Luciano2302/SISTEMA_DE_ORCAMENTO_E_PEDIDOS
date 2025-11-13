package menu.impl;

import menu.MenuOption;
import usecase.impl.ListarProdutosUseCase;

public class ListarProdutosMenuOption implements MenuOption {
    
    private final ListarProdutosUseCase useCase;
    
    public ListarProdutosMenuOption(ListarProdutosUseCase useCase) {
        this.useCase = useCase;
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== LISTA DE PRODUTOS ===");
        var produtos = useCase.execute(null);
        
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        
        for (int i = 0; i < produtos.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, produtos.get(i));
        }
    }
    
    @Override
    public String getDescription() {
        return "Listar Produtos";
    }
    
    @Override
    public int getOrder() {
        return 4;
    }
}