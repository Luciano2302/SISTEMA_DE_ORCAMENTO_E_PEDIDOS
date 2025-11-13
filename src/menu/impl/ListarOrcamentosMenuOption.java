package menu.impl;

import menu.MenuOption;
import usecase.impl.ListarOrcamentosUseCase;

public class ListarOrcamentosMenuOption implements MenuOption {
    
    private final ListarOrcamentosUseCase useCase;
    
    public ListarOrcamentosMenuOption(ListarOrcamentosUseCase useCase) {
        this.useCase = useCase;
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== LISTA DE ORÇAMENTOS ===");
        var orcamentos = useCase.execute(null);
        
        if (orcamentos.isEmpty()) {
            System.out.println("Nenhum orçamento criado.");
            return;
        }
        
        for (var orcamento : orcamentos) {
            System.out.printf("Orçamento #%d | Cliente: %s | Status: %s | Total: R$ %.2f\n",
                orcamento.getNumero(), orcamento.getCliente().getNome(),
                orcamento.getStatus(), orcamento.calcularValorTotal());
            
            for (var item : orcamento.getItens()) {
                System.out.printf("   - %s\n", item);
            }
            System.out.println();
        }
    }
    
    @Override
    public String getDescription() {
        return "Listar Orçamentos";
    }
    
    @Override
    public int getOrder() {
        return 8;
    }
}