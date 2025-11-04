import model.*;
import service.OrcamentoService;
import service.PedidoService;
import service.FaturaService;
import model.enums.StatusOrcamento;
import model.enums.StatusPedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static OrcamentoService orcamentoService = new OrcamentoService();
    private static PedidoService pedidoService = new PedidoService();
    private static FaturaService faturaService = new FaturaService();
    private static List < Produto > produtos = new ArrayList < > ();
    private static List < Servico > servicos = new ArrayList < > ();
    private static List < Cliente > clientes = new ArrayList < > ();
    private static List < Orcamento > orcamentos = new ArrayList < > ();
    private static List < Pedido > pedidos = new ArrayList < > ();
    private static List < Fatura > faturas = new ArrayList < > ();
    private static List < NotaFiscal > notasFiscais = new ArrayList < > ();

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTÃO DE ORÇAMENTOS E PEDIDOS ===\n");

        cadastrarDadosExemplo();

        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            processarOpcao(opcao);
        } while (opcao != 0);

        scanner.close();
    }

    private static void cadastrarDadosExemplo() {
        produtos.add(new Produto("P001", "Notebook Dell", 2500.00, 10));
        produtos.add(new Produto("P002", "Mouse Wireless", 89.90, 50));
        produtos.add(new Produto("P003", "Teclado Mecânico", 150.00, 30));

        servicos.add(new Servico("S001", "Instalação Office", 150.00, 2));
        servicos.add(new Servico("S002", "Configuração Rede", 200.00, 3));
        servicos.add(new Servico("S003", "Backup Dados", 100.00, 1));

        clientes.add(new Cliente("João Silva", "joao@email.com", "(11) 99999-9999"));
        clientes.add(new Cliente("Maria Santos", "maria@email.com", "(11) 88888-8888"));
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Cadastrar Serviço");
        System.out.println("3 - Cadastrar Cliente");
        System.out.println("4 - Listar Produtos");
        System.out.println("5 - Listar Serviços");
        System.out.println("6 - Listar Clientes");
        System.out.println("7 - Criar Orçamento");
        System.out.println("8 - Listar Orçamentos");
        System.out.println("9 - Aprovar Orçamento");
        System.out.println("10 - Gerar Pedido");
        System.out.println("11 - Listar Pedidos");
        System.out.println("12 - Confirmar Pedido");
        System.out.println("13 - Gerar Fatura");
        System.out.println("14 - Listar Faturas");
        System.out.println("15 - Executar Fluxo Completo (Demo)");
        System.out.println("0 - Sair");
        System.out.println("======================");
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarProduto();
                break;
            case 2:
                cadastrarServico();
                break;
            case 3:
                cadastrarCliente();
                break;
            case 4:
                listarProdutos();
                break;
            case 5:
                listarServicos();
                break;
            case 6:
                listarClientes();
                break;
            case 7:
                criarOrcamento();
                break;
            case 8:
                listarOrcamentos();
                break;
            case 9:
                aprovarOrcamento();
                break;
            case 10:
                gerarPedido();
                break;
            case 11:
                listarPedidos();
                break;
            case 12:
                confirmarPedido();
                break;
            case 13:
                gerarFatura();
                break;
            case 14:
                listarFaturas();
                break;
            case 15:
                executarFluxoCompleto();
                break;
            case 0:
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void cadastrarProduto() {
        System.out.println("\n=== CADASTRAR PRODUTO ===");
        String codigo = lerString("Código: ");
        String descricao = lerString("Descrição: ");
        double preco = lerDouble("Preço: R$ ");
        int estoque = lerInteiro("Estoque: ");

        Produto produto = new Produto(codigo, descricao, preco, estoque);
        produtos.add(produto);
        System.out.println("Produto cadastrado: " + produto.getDescricao());
    }

    private static void cadastrarServico() {
        System.out.println("\n=== CADASTRAR SERVIÇO ===");
        String codigo = lerString("Código: ");
        String descricao = lerString("Descrição: ");
        double preco = lerDouble("Preço: R$ ");
        int duracao = lerInteiro("Duração (horas): ");

        Servico servico = new Servico(codigo, descricao, preco, duracao);
        servicos.add(servico);
        System.out.println("Serviço cadastrado: " + servico.getDescricao());
    }

    private static void cadastrarCliente() {
        System.out.println("\n=== CADASTRAR CLIENTE ===");
        String nome = lerString("Nome: ");
        String email = lerString("Email: ");
        String telefone = lerString("Telefone: ");

        Cliente cliente = new Cliente(nome, email, telefone);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado: " + cliente.getNome());
    }

    private static void listarProdutos() {
        System.out.println("\n=== LISTA DE PRODUTOS ===");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            System.out.printf("%d - %s | %s | R$ %.2f | Estoque: %d\n",
                i + 1, p.getCodigo(), p.getDescricao(), p.getPreco(), p.getEstoque());
        }
    }

    private static void listarServicos() {
        System.out.println("\n=== LISTA DE SERVIÇOS ===");
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
            return;
        }
        for (int i = 0; i < servicos.size(); i++) {
            Servico s = servicos.get(i);
            System.out.printf("%d - %s | %s | R$ %.2f | %d horas\n",
                i + 1, s.getCodigo(), s.getDescricao(), s.getPreco(), s.getDuracaoHoras());
        }
    }

    private static void listarClientes() {
        System.out.println("\n=== LISTA DE CLIENTES ===");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            System.out.printf("%d - %s | %s | %s\n",
                i + 1, c.getNome(), c.getEmail(), c.getTelefone());
        }
    }

    private static void criarOrcamento() {
        System.out.println("\n=== CRIAR ORÇAMENTO ===");

        listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Cadastre um cliente primeiro!");
            return;
        }
        int clienteIndex = lerInteiro("Selecione o cliente (número): ") - 1;
        if (clienteIndex < 0 || clienteIndex >= clientes.size()) {
            System.out.println("Cliente inválido!");
            return;
        }
        Cliente cliente = clientes.get(clienteIndex);

        Orcamento orcamento = orcamentoService.criarOrcamento(cliente);
        orcamentos.add(orcamento);
        System.out.println("Orçamento #" + orcamento.getNumero() + " criado para: " + cliente.getNome());

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== ADICIONAR ITEM ===");
            System.out.println("1 - Adicionar Produto");
            System.out.println("2 - Adicionar Serviço");
            System.out.println("0 - Finalizar orçamento");

            int tipoOpcao = lerInteiro("Escolha: ");

            switch (tipoOpcao) {
                case 1:
                    adicionarProdutoAoOrcamento(orcamento);
                    break;
                case 2:
                    adicionarServicoAoOrcamento(orcamento);
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        System.out.println("\n=== RESUMO DO ORÇAMENTO ===");
        System.out.println("Itens no orçamento:");
        for (ItemOrcamento item: orcamento.getItens()) {
            System.out.printf(" - %s x %d = R$ %.2f\n",
                item.getItem().getDescricao(), item.getQuantidade(), item.getValorTotal());
        }

        double total = orcamentoService.calcularTotal(orcamento);
        System.out.printf("VALOR TOTAL: R$ %.2f\n", total);

        orcamentoService.enviarParaAprovacao(orcamento);
        System.out.println("Orçamento enviado para aprovação!");
    }

    private static void adicionarProdutoAoOrcamento(Orcamento orcamento) {
        listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado!");
            return;
        }

        int produtoIndex = lerInteiro("Selecione o produto (número): ") - 1;
        if (produtoIndex < 0 || produtoIndex >= produtos.size()) {
            System.out.println(" Produto inválido!");
            return;
        }

        Produto produto = produtos.get(produtoIndex);
        int quantidade = lerInteiro("Quantidade: ");

        orcamentoService.adicionarItem(orcamento, produto, quantidade);
        System.out.println("" + produto.getDescricao() + " adicionado ao orçamento!");
    }

    private static void adicionarServicoAoOrcamento(Orcamento orcamento) {
        listarServicos();
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado!");
            return;
        }

        int servicoIndex = lerInteiro("Selecione o serviço (número): ") - 1;
        if (servicoIndex < 0 || servicoIndex >= servicos.size()) {
            System.out.println("Serviço inválido!");
            return;
        }

        Servico servico = servicos.get(servicoIndex);
        int quantidade = lerInteiro("Quantidade: ");

        orcamentoService.adicionarItem(orcamento, servico, quantidade);
        System.out.println("" + servico.getDescricao() + " adicionado ao orçamento!");
    }

    private static void listarOrcamentos() {
        System.out.println("\n=== LISTA DE ORÇAMENTOS ===");
        if (orcamentos.isEmpty()) {
            System.out.println("Nenhum orçamento criado.");
            return;
        }

        for (Orcamento orcamento: orcamentos) {
            System.out.printf("Orçamento #%d | Cliente: %s | Status: %s | Total: R$ %.2f\n",
                orcamento.getNumero(), orcamento.getCliente().getNome(),
                orcamento.getStatus(), orcamento.calcularValorTotal());

            for (ItemOrcamento item: orcamento.getItens()) {
                System.out.printf("   - %s x %d = R$ %.2f\n",
                    item.getItem().getDescricao(), item.getQuantidade(), item.getValorTotal());
            }
            System.out.println();
        }
    }

    private static void aprovarOrcamento() {
        System.out.println("\n=== APROVAR ORÇAMENTO ===");
        listarOrcamentos();

        if (orcamentos.isEmpty()) {
            System.out.println("Nenhum orçamento para aprovar!");
            return;
        }

        int numero = lerInteiro("Número do orçamento para aprovar: ");

        Orcamento orcamento = buscarOrcamentoPorNumero(numero);
        if (orcamento != null) {
            orcamentoService.aprovarOrcamento(orcamento);
            System.out.println("Orçamento #" + numero + " aprovado com sucesso!");
        } else {
            System.out.println("Orçamento #" + numero + " não encontrado!");
        }
    }

    private static void gerarPedido() {
        System.out.println("\n=== GERAR PEDIDO ===");

        List < Orcamento > orcamentosAprovados = orcamentos.stream()
            .filter(o -> o.getStatus() == StatusOrcamento.APROVADO)
            .toList();

        if (orcamentosAprovados.isEmpty()) {
            System.out.println("Nenhum orçamento aprovado disponível!");
            return;
        }

        System.out.println("Orçamentos Aprovados:");
        for (Orcamento orcamento: orcamentosAprovados) {
            System.out.printf("Orçamento #%d | Cliente: %s | Total: R$ %.2f\n",
                orcamento.getNumero(), orcamento.getCliente().getNome(),
                orcamento.calcularValorTotal());
        }

        int numero = lerInteiro("Número do orçamento aprovado: ");
        Orcamento orcamento = buscarOrcamentoPorNumero(numero);

        if (orcamento != null && orcamento.getStatus() == StatusOrcamento.APROVADO) {
            try {
                Pedido pedido = pedidoService.criarPedidoFromOrcamento(orcamento);
                pedidos.add(pedido);
                System.out.println("Pedido #" + pedido.getNumero() + " gerado com sucesso!");
                System.out.println("Valor: R$ " + pedido.getValorTotal());
            } catch (IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Orçamento não encontrado ou não está aprovado!");
        }
    }

    private static void listarPedidos() {
        System.out.println("\n=== LISTA DE PEDIDOS ===");
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido gerado.");
            return;
        }

        for (Pedido pedido: pedidos) {
            String statusIcon = "";
            switch (pedido.getStatus()) {
                case ABERTO:
                    statusIcon = "🟡";
                    break;
                case CONFIRMADO:
                    statusIcon = "🟢";
                    break;
                case FATURADO:
                    statusIcon = "🔵";
                    break;
                default:
                    statusIcon = "⚪";
            }

            System.out.printf("%s Pedido #%d | Orçamento: #%d | Status: %s | Total: R$ %.2f\n",
                statusIcon, pedido.getNumero(), pedido.getOrcamento().getNumero(),
                pedido.getStatus(), pedido.getValorTotal());
        }
    }

    private static void gerarFatura() {
        System.out.println("\n=== GERAR FATURA ===");

        List < Pedido > pedidosConfirmados = pedidos.stream()
            .filter(p -> p.getStatus() == StatusPedido.CONFIRMADO)
            .toList();

        if (pedidosConfirmados.isEmpty()) {
            System.out.println("Nenhum pedido confirmado disponível!");
            System.out.println("Dica: Confirme o pedido primeiro usando a opção 12");
            return;
        }

        System.out.println("Pedidos Confirmados:");
        for (Pedido pedido: pedidosConfirmados) {
            System.out.printf("Pedido #%d | Cliente: %s | Total: R$ %.2f\n",
                pedido.getNumero(), pedido.getOrcamento().getCliente().getNome(),
                pedido.getValorTotal());
        }

        int numero = lerInteiro("Número do pedido: ");
        Pedido pedido = buscarPedidoPorNumero(numero);

        if (pedido != null && pedido.getStatus() == StatusPedido.CONFIRMADO) {
            try {
                Fatura fatura = faturaService.criarFatura(pedido);
                faturas.add(fatura);
                System.out.println("Fatura #" + fatura.getNumero() + " gerada com sucesso!");
                System.out.println("Valor: R$ " + fatura.getValorTotal());

                faturaService.pagarFatura(fatura);
                System.out.println("Fatura paga com sucesso!");

                NotaFiscal notaFiscal = faturaService.emitirNotaFiscal(fatura);
                notasFiscais.add(notaFiscal);
                System.out.println("Nota Fiscal #" + notaFiscal.getNumero() + " emitida!");

            } catch (IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Pedido não encontrado ou não está confirmado!");
            System.out.println("Status atual do pedido: " +
                (pedido != null ? pedido.getStatus() : "NÃO ENCONTRADO"));
        }
    }

    private static void confirmarPedido() {
        System.out.println("\n=== CONFIRMAR PEDIDO ===");

        List < Pedido > pedidosAbertos = pedidos.stream()
            .filter(p -> p.getStatus() == StatusPedido.ABERTO)
            .toList();

        if (pedidosAbertos.isEmpty()) {
            System.out.println("Nenhum pedido em aberto disponível para confirmação!");
            return;
        }

        System.out.println("Pedidos em Aberto:");
        for (Pedido pedido: pedidosAbertos) {
            System.out.printf("Pedido #%d | Cliente: %s | Total: R$ %.2f\n",
                pedido.getNumero(), pedido.getOrcamento().getCliente().getNome(),
                pedido.getValorTotal());
        }

        int numero = lerInteiro("Número do pedido para confirmar: ");
        Pedido pedido = buscarPedidoPorNumero(numero);

        if (pedido != null && pedido.getStatus() == StatusPedido.ABERTO) {
            pedidoService.confirmarPedido(pedido);
            System.out.println("Pedido #" + numero + " confirmado com sucesso!");
            System.out.println("Status atual: " + pedido.getStatus());
        } else {
            System.out.println("Pedido não encontrado ou não está em aberto!");
        }
    }

    private static void listarFaturas() {
        System.out.println("\n=== LISTA DE FATURAS ===");
        if (faturas.isEmpty()) {
            System.out.println("Nenhuma fatura gerada.");
            return;
        }

        for (Fatura fatura: faturas) {
            System.out.printf("Fatura #%d | Pedido: #%d | Status: %s | Total: R$ %.2f\n",
                fatura.getNumero(), fatura.getPedido().getNumero(),
                fatura.getStatus(), fatura.getValorTotal());
        }
    }

    private static Orcamento buscarOrcamentoPorNumero(int numero) {
        return orcamentos.stream()
            .filter(o -> o.getNumero() == numero)
            .findFirst()
            .orElse(null);
    }

    private static Pedido buscarPedidoPorNumero(int numero) {
        return pedidos.stream()
            .filter(p -> p.getNumero() == numero)
            .findFirst()
            .orElse(null);
    }

    private static void executarFluxoCompleto() {
        System.out.println("\n=== EXECUTANDO FLUXO COMPLETO DEMO ===");

        Cliente cliente = new Cliente("João Silva", "joao@email.com", "(11) 99999-9999");
        Produto produto1 = new Produto("P001", "Notebook Dell", 2500.00, 10);
        Produto produto2 = new Produto("P002", "Mouse Wireless", 89.90, 50);
        Servico servico1 = new Servico("S001", "Instalação Office", 150.00, 2);

        Orcamento orcamento = orcamentoService.criarOrcamento(cliente);
        orcamentoService.adicionarItem(orcamento, produto1, 1);
        orcamentoService.adicionarItem(orcamento, produto2, 2);
        orcamentoService.adicionarItem(orcamento, servico1, 1);

        orcamentoService.enviarParaAprovacao(orcamento);
        orcamentoService.aprovarOrcamento(orcamento);

        Pedido pedido = pedidoService.criarPedidoFromOrcamento(orcamento);
        pedidoService.confirmarPedido(pedido);

        Fatura fatura = faturaService.criarFatura(pedido);
        faturaService.pagarFatura(fatura);
        NotaFiscal notaFiscal = faturaService.emitirNotaFiscal(fatura);

        System.out.println("Fluxo completo executado com sucesso!");
        System.out.println("Resumo:");
        System.out.println("- Orçamento: #" + orcamento.getNumero());
        System.out.println("- Pedido: #" + pedido.getNumero());
        System.out.println("- Fatura: #" + fatura.getNumero());
        System.out.println("- Nota Fiscal: #" + notaFiscal.getNumero());
        System.out.println("- Valor Total: R$ " + orcamento.calcularValorTotal());
    }

    private static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um valor válido!");
            }
        }
    }
}