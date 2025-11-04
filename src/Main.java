import model.*;
import service.OrcamentoService;
import service.PedidoService;
import service.FaturaService;
import model.enums.StatusOrcamento;
import model.enums.StatusPedido;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTÃO DE ORÇAMENTOS E PEDIDOS ===\n");
        
        // Criando serviços (GRASP - Controller)
        OrcamentoService orcamentoService = new OrcamentoService();
        PedidoService pedidoService = new PedidoService();
        FaturaService faturaService = new FaturaService();
        
        // Cadastrando cliente
        Cliente cliente = new Cliente("João Silva", "joao@email.com", "(11) 99999-9999");
        
        // Cadastrando produtos e serviços
        Produto produto1 = new Produto("P001", "Notebook Dell", 2500.00, 10);
        Produto produto2 = new Produto("P002", "Mouse Wireless", 89.90, 50);
        Servico servico1 = new Servico("S001", "Instalação Office", 150.00, 2);
        
        System.out.println("=== CADASTRO DE PRODUTOS E SERVIÇOS ===");
        System.out.println("Produto: " + produto1.getDescricao() + " - R$ " + produto1.getPreco());
        System.out.println("Produto: " + produto2.getDescricao() + " - R$ " + produto2.getPreco());
        System.out.println("Serviço: " + servico1.getDescricao() + " - R$ " + servico1.getPreco());
        
        // Criando orçamento
        System.out.println("\n=== CRIAÇÃO DE ORÇAMENTO ===");
        Orcamento orcamento = orcamentoService.criarOrcamento(cliente);
        System.out.println("Orçamento #" + orcamento.getNumero() + " criado para: " + cliente.getNome());
        
        // Adicionando itens ao orçamento
        orcamentoService.adicionarItem(orcamento, produto1, 1);
        orcamentoService.adicionarItem(orcamento, produto2, 2);
        orcamentoService.adicionarItem(orcamento, servico1, 1);
        
        // Calculando total
        double total = orcamentoService.calcularTotal(orcamento);
        System.out.println("Itens adicionados ao orçamento:");
        for (ItemOrcamento item : orcamento.getItens()) {
            System.out.println(" - " + item.getItem().getDescricao() + 
                             " x " + item.getQuantidade() + 
                             " = R$ " + item.getValorTotal());
        }
        System.out.println("VALOR TOTAL: R$ " + total);
        
        // Enviando para aprovação
        System.out.println("\n=== FLUXO DE APROVAÇÃO ===");
        System.out.println("Status atual: " + orcamento.getStatus());
        orcamentoService.enviarParaAprovacao(orcamento);
        System.out.println("Status após envio: " + orcamento.getStatus());
        
        // Aprovando orçamento
        orcamentoService.aprovarOrcamento(orcamento);
        System.out.println("Status após aprovação: " + orcamento.getStatus());
        
        // Convertendo em pedido
        System.out.println("\n=== CONVERSÃO EM PEDIDO ===");
        if (orcamentoService.podeSerConvertidoEmPedido(orcamento)) {
            Pedido pedido = pedidoService.criarPedidoFromOrcamento(orcamento);
            System.out.println("Pedido #" + pedido.getNumero() + " criado com sucesso!");
            System.out.println("Status do pedido: " + pedido.getStatus());
            System.out.println("Valor do pedido: R$ " + pedido.getValorTotal());
            
            // Confirmando pedido
            pedidoService.confirmarPedido(pedido);
            System.out.println("Status após confirmação: " + pedido.getStatus());
            
            // NOVO: Fluxo de Fatura e Nota Fiscal
            System.out.println("\n=== FLUXO DE FATURA ===");
            try {
                Fatura fatura = faturaService.criarFatura(pedido);
                System.out.println("Fatura #" + fatura.getNumero() + " criada com sucesso!");
                System.out.println("Status da fatura: " + fatura.getStatus());
                System.out.println("Valor da fatura: R$ " + fatura.getValorTotal());
                
                // Pagando fatura
                faturaService.pagarFatura(fatura);
                System.out.println("Status após pagamento: " + fatura.getStatus());
                
                // Emitindo nota fiscal
                NotaFiscal notaFiscal = faturaService.emitirNotaFiscal(fatura);
                System.out.println("Nota Fiscal #" + notaFiscal.getNumero() + " emitida com sucesso!");
                System.out.println("Valor da nota fiscal: R$ " + notaFiscal.getValorTotal());
                
            } catch (IllegalStateException e) {
                System.out.println("Erro no fluxo de fatura: " + e.getMessage());
            }
        }
        
        System.out.println("\n=== PROCESSO CONCLUÍDO ===");
    }
}