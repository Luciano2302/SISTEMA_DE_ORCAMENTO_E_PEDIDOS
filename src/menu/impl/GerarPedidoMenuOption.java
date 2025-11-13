package menu.impl;

import menu.MenuOption;
import usecase.impl.GerarPedidoUseCase;
import usecase.impl.ListarOrcamentosUseCase;
import java.util.Scanner;

public class GerarPedidoMenuOption implements MenuOption {
    
    private final GerarPedidoUseCase useCase;
    private final ListarOrcamentosUseCase listarOrcamentosUseCase;
    private final Scanner scanner;
    
    public GerarPedidoMenuOption(
        GerarPedidoUseCase useCase,
        ListarOrcamentosUseCase listarOrcamentosUseCase,
        Scanner scanner
    ) {
        this.useCase = useCase;
        this.listarOrcamentosUseCase = listarOrcamentosUseCase;
        this.scanner = scanner;
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== GERAR PEDIDO ===");
        
        var orcamentos = listarOrcamentosUseCase.execute(null);
        var orcamentosAprovados = orcamentos.stream()
            .filter(o -> o.getStatus().name().equals("APROVADO"))
            .toList();
            
        if (orcamentosAprovados.isEmpty()) {
            System.out.println("Nenhum orçamento aprovado disponível!");
            return;
        }
        
        for (var orcamento : orcamentosAprovados) {
            System.out.printf("Orçamento #%d | Cliente: %s | Total: R$ %.2f\n",
                orcamento.getNumero(), orcamento.getCliente().getNome(),
                orcamento.calcularValorTotal());
        }
        
        int numero = lerInteiro("Número do orçamento aprovado: ");
        
        var orcamentoSelecionado = orcamentosAprovados.stream()
            .filter(o -> o.getNumero() == numero)
            .findFirst();
            
        if (orcamentoSelecionado.isPresent()) {
            try {
                var pedido = useCase.execute(orcamentoSelecionado.get());
                System.out.println("✅ Pedido #" + pedido.getNumero() + " gerado com sucesso!");
            } catch (Exception e) {
                System.out.println("❌ Erro ao gerar pedido: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Orçamento não encontrado!");
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
        return "Gerar Pedido";
    }
    
    @Override
    public int getOrder() {
        return 10;
    }
}