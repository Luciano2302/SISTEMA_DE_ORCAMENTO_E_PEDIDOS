package menu.impl;

import menu.MenuOption;
import usecase.impl.GerarFaturaUseCase;
import usecase.impl.ListarPedidosUseCase;
import service.FaturaService;
import repository.impl.FaturaRepositoryImpl;
import java.util.Scanner;

public class GerarFaturaMenuOption implements MenuOption {
    
    private final GerarFaturaUseCase useCase;
    private final ListarPedidosUseCase listarPedidosUseCase;
    private final FaturaService faturaService;
    private final FaturaRepositoryImpl faturaRepository;
    private final Scanner scanner;
    
    public GerarFaturaMenuOption(
        GerarFaturaUseCase useCase,
        ListarPedidosUseCase listarPedidosUseCase,
        FaturaService faturaService,
        FaturaRepositoryImpl faturaRepository,
        Scanner scanner
    ) {
        this.useCase = useCase;
        this.listarPedidosUseCase = listarPedidosUseCase;
        this.faturaService = faturaService;
        this.faturaRepository = faturaRepository;
        this.scanner = scanner;
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== GERAR FATURA ===");
        
        var pedidos = listarPedidosUseCase.execute(null);
        var pedidosConfirmados = pedidos.stream()
            .filter(p -> p.getStatus().name().equals("CONFIRMADO"))
            .toList();
            
        if (pedidosConfirmados.isEmpty()) {
            System.out.println("Nenhum pedido confirmado disponível!");
            System.out.println("Dica: Confirme o pedido primeiro usando a opção 12");
            return;
        }
        
        System.out.println("Pedidos Confirmados:");
        for (var pedido : pedidosConfirmados) {
            System.out.printf("Pedido #%d | Cliente: %s | Total: R$ %.2f\n",
                pedido.getNumero(), pedido.getOrcamento().getCliente().getNome(),
                pedido.getValorTotal());
        }
        
        int numero = lerInteiro("Número do pedido: ");
        
        var pedidoSelecionado = pedidosConfirmados.stream()
            .filter(p -> p.getNumero() == numero)
            .findFirst();
            
        if (pedidoSelecionado.isPresent()) {
            try {
                var fatura = useCase.execute(pedidoSelecionado.get());
                faturaRepository.salvar(fatura);
                faturaService.pagarFatura(fatura);
                faturaRepository.salvar(fatura);
                
                var notaFiscal = faturaService.emitirNotaFiscal(fatura);
                
                System.out.println("✅ Fatura #" + fatura.getNumero() + " gerada com sucesso!");
                System.out.println("✅ Fatura paga com sucesso!");
                System.out.println("✅ Nota Fiscal #" + notaFiscal.getNumero() + " emitida!");
                
            } catch (Exception e) {
                System.out.println("❌ Erro ao gerar fatura: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Pedido não encontrado ou não está confirmado!");
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
        return "Gerar Fatura";
    }
    
    @Override
    public int getOrder() {
        return 13;
    }
}