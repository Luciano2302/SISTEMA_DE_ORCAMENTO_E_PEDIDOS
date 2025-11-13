package menu.impl;

import menu.MenuOption;
import repository.impl.FaturaRepositoryImpl;

public class ListarFaturasMenuOption implements MenuOption {
    
    private final FaturaRepositoryImpl faturaRepository;
    
    public ListarFaturasMenuOption(FaturaRepositoryImpl faturaRepository) {
        this.faturaRepository = faturaRepository;
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== LISTA DE FATURAS ===");
        var faturas = faturaRepository.listarTodos();
        
        if (faturas.isEmpty()) {
            System.out.println("Nenhuma fatura gerada.");
            return;
        }
        
        for (var fatura : faturas) {
            String statusIcon = "";
            switch (fatura.getStatus().name()) {
                case "ABERTA" -> statusIcon = "🟡";
                case "PAGA" -> statusIcon = "🟢";
                case "CANCELADA" -> statusIcon = "❌";
                case "ATRASADA" -> statusIcon = "🔴";
                default -> statusIcon = "⚪";
            }
            
            System.out.printf("%s Fatura #%d | Pedido: #%d | Status: %s | Total: R$ %.2f\n",
                statusIcon, fatura.getNumero(), fatura.getPedido().getNumero(),
                fatura.getStatus(), fatura.getValorTotal());
        }
    }
    
    @Override
    public String getDescription() {
        return "Listar Faturas";
    }
    
    @Override
    public int getOrder() {
        return 14;
    }
}