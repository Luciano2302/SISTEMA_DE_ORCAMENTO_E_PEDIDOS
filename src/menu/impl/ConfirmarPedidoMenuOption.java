package menu.impl;

import menu.MenuOption;
import usecase.impl.ConfirmarPedidoUseCase;
import usecase.impl.ListarPedidosUseCase;
import java.util.Scanner;

public class ConfirmarPedidoMenuOption implements MenuOption {
    
    private final ConfirmarPedidoUseCase useCase;
    private final ListarPedidosUseCase listarPedidosUseCase;
    private final Scanner scanner;
    
    public ConfirmarPedidoMenuOption(
        ConfirmarPedidoUseCase useCase,
        ListarPedidosUseCase listarPedidosUseCase,
        Scanner scanner
    ) {
        this.useCase = useCase;
        this.listarPedidosUseCase = listarPedidosUseCase;
        this.scanner = scanner;
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== CONFIRMAR PEDIDO ===");
        
        var pedidos = listarPedidosUseCase.execute(null);
        var pedidosAbertos = pedidos.stream()
            .filter(p -> p.getStatus().name().equals("ABERTO"))
            .toList();
            
        if (pedidosAbertos.isEmpty()) {
            System.out.println("Nenhum pedido em aberto disponível para confirmação!");
            return;
        }
        
        System.out.println("Pedidos em Aberto:");
        for (var pedido : pedidosAbertos) {
            System.out.printf("Pedido #%d | Cliente: %s | Total: R$ %.2f\n",
                pedido.getNumero(), pedido.getOrcamento().getCliente().getNome(),
                pedido.getValorTotal());
        }
        
        int numero = lerInteiro("Número do pedido para confirmar: ");
        
        var pedidoSelecionado = pedidosAbertos.stream()
            .filter(p -> p.getNumero() == numero)
            .findFirst();
            
        if (pedidoSelecionado.isPresent()) {
            try {
                useCase.execute(pedidoSelecionado.get());
                System.out.println("✅ Pedido #" + numero + " confirmado com sucesso!");
                System.out.println("Status atual: " + pedidoSelecionado.get().getStatus());
            } catch (Exception e) {
                System.out.println("❌ Erro ao confirmar pedido: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Pedido não encontrado ou não está em aberto!");
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
    
    @Override
    public String getDescription() {
        return "Confirmar Pedido";
    }
    
    @Override
    public int getOrder() {
        return 12;
    }
}