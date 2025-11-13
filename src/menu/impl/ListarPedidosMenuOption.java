package menu.impl;

import menu.MenuOption;
import usecase.impl.ListarPedidosUseCase;

public class ListarPedidosMenuOption implements MenuOption {
    
    private final ListarPedidosUseCase useCase;
    
    public ListarPedidosMenuOption(ListarPedidosUseCase useCase) {
        this.useCase = useCase;
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== LISTA DE PEDIDOS ===");
        var pedidos = useCase.execute(null);
        
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido gerado.");
            return;
        }
        
        for (var pedido : pedidos) {
            String statusIcon = "";
            switch (pedido.getStatus().name()) {
                case "ABERTO" -> statusIcon = "🟡";
                case "CONFIRMADO" -> statusIcon = "🟢";
                case "FATURADO" -> statusIcon = "🔵";
                case "ENTREGUE" -> statusIcon = "✅";
                case "CANCELADO" -> statusIcon = "❌";
                default -> statusIcon = "⚪";
            }

            System.out.printf("%s Pedido #%d | Orçamento: #%d | Status: %s | Total: R$ %.2f\n",
                statusIcon, pedido.getNumero(), pedido.getOrcamento().getNumero(),
                pedido.getStatus(), pedido.getValorTotal());
        }
    }
    
    @Override
    public String getDescription() {
        return "Listar Pedidos";
    }
    
    @Override
    public int getOrder() {
        return 11;
    }
}