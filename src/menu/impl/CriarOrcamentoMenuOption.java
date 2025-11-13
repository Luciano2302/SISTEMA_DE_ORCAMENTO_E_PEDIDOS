package menu.impl;

import menu.MenuOption;
import usecase.impl.CriarOrcamentoUseCase;
import usecase.impl.ListarClientesUseCase;
import usecase.impl.AdicionarItemOrcamentoUseCase;
import repository.ClienteRepository;
import repository.ProdutoRepository;
import repository.ServicoRepository;
import model.Cliente;
import model.Produto;
import model.Servico;
import model.Orcamento;
import java.util.Scanner;
import java.util.List;

public class CriarOrcamentoMenuOption implements MenuOption {
    
    private final CriarOrcamentoUseCase criarOrcamentoUseCase;
    private final ListarClientesUseCase listarClientesUseCase;
    private final AdicionarItemOrcamentoUseCase adicionarItemUseCase;
    private final Scanner scanner;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ServicoRepository servicoRepository;
    
    public CriarOrcamentoMenuOption(
        CriarOrcamentoUseCase criarOrcamentoUseCase,
        ListarClientesUseCase listarClientesUseCase,
        AdicionarItemOrcamentoUseCase adicionarItemUseCase,
        Scanner scanner,
        ClienteRepository clienteRepository,
        ProdutoRepository produtoRepository,
        ServicoRepository servicoRepository
    ) {
        this.criarOrcamentoUseCase = criarOrcamentoUseCase;
        this.listarClientesUseCase = listarClientesUseCase;
        this.adicionarItemUseCase = adicionarItemUseCase;
        this.scanner = scanner;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.servicoRepository = servicoRepository;
    }
    
    @Override
    public void execute() {
        System.out.println("\n=== CRIAR ORÇAMENTO ===");
        
        // Listar e selecionar cliente
        var clientes = listarClientesUseCase.execute(null);
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            return;
        }
        
        for (int i = 0; i < clientes.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, clientes.get(i));
        }
        
        int clienteIndex = lerInteiro("Selecione o cliente (número): ") - 1;
        if (clienteIndex < 0 || clienteIndex >= clientes.size()) {
            System.out.println("Cliente inválido!");
            return;
        }
        
        Cliente cliente = clientes.get(clienteIndex);
        Orcamento orcamento = criarOrcamentoUseCase.execute(cliente);
        System.out.println("✅ Orçamento #" + orcamento.getNumero() + " criado para: " + cliente.getNome());
        
        // Adicionar itens
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== ADICIONAR ITEM ===");
            System.out.println("1 - Adicionar Produto");
            System.out.println("2 - Adicionar Serviço");
            System.out.println("0 - Finalizar orçamento");
            
            int opcao = lerInteiro("Escolha: ");
            
            switch (opcao) {
                case 1 -> adicionarProduto(orcamento);
                case 2 -> adicionarServico(orcamento);
                case 0 -> continuar = false;
                default -> System.out.println("Opção inválida!");
            }
        }
        
        // Resumo do orçamento
        System.out.println("\n=== RESUMO DO ORÇAMENTO ===");
        System.out.println("Itens no orçamento:");
        for (var item : orcamento.getItens()) {
            System.out.printf(" - %s\n", item);
        }
        System.out.printf("VALOR TOTAL: R$ %.2f\n", orcamento.calcularValorTotal());
        
        orcamento.enviarParaAprovacao();
        System.out.println("Orçamento enviado para aprovação!");
    }
    
    private void adicionarProduto(Orcamento orcamento) {
        var produtos = produtoRepository.listarTodos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado!");
            return;
        }
        
        for (int i = 0; i < produtos.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, produtos.get(i));
        }
        
        int produtoIndex = lerInteiro("Selecione o produto (número): ") - 1;
        if (produtoIndex < 0 || produtoIndex >= produtos.size()) {
            System.out.println("Produto inválido!");
            return;
        }
        
        Produto produto = produtos.get(produtoIndex);
        int quantidade = lerInteiro("Quantidade: ");
        
        try {
            var input = new AdicionarItemOrcamentoUseCase.Input(orcamento, produto, quantidade);
            adicionarItemUseCase.execute(input);
            System.out.println("✅ " + produto.getDescricao() + " adicionado ao orçamento!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao adicionar produto: " + e.getMessage());
        }
    }
    
    private void adicionarServico(Orcamento orcamento) {
        var servicos = servicoRepository.listarTodos();
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado!");
            return;
        }
        
        for (int i = 0; i < servicos.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, servicos.get(i));
        }
        
        int servicoIndex = lerInteiro("Selecione o serviço (número): ") - 1;
        if (servicoIndex < 0 || servicoIndex >= servicos.size()) {
            System.out.println("Serviço inválido!");
            return;
        }
        
        Servico servico = servicos.get(servicoIndex);
        int quantidade = lerInteiro("Quantidade: ");
        
        try {
            var input = new AdicionarItemOrcamentoUseCase.Input(orcamento, servico, quantidade);
            adicionarItemUseCase.execute(input);
            System.out.println("✅ " + servico.getDescricao() + " adicionado ao orçamento!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao adicionar serviço: " + e.getMessage());
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
        return "Criar Orçamento";
    }
    
    @Override
    public int getOrder() {
        return 7;
    }
}