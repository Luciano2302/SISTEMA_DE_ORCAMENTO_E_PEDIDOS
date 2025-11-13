import menu.Menu;
import menu.MenuOption;
import menu.impl.MenuPrincipal;
import menu.impl.*;
import repository.impl.*;
import usecase.impl.*;
import service.FaturaService;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTÃO DE ORÇAMENTOS E PEDIDOS (SOLID) ===\n");
        
        // Inicialização de dependências
        var produtoRepository = new ProdutoRepositoryImpl();
        var servicoRepository = new ServicoRepositoryImpl();
        var clienteRepository = new ClienteRepositoryImpl();
        var orcamentoRepository = new OrcamentoRepositoryImpl();
        var pedidoRepository = new PedidoRepositoryImpl();
        var faturaRepository = new FaturaRepositoryImpl();
        
        var faturaService = new FaturaService();
        var scanner = new Scanner(System.in);
        
        // Configuração das opções do menu
        List<MenuOption> menuOptions = Arrays.asList(
            new CadastrarProdutoMenuOption(new CadastrarProdutoUseCase(produtoRepository), scanner),
            new CadastrarServicoMenuOption(new CadastrarServicoUseCase(servicoRepository), scanner),
            new CadastrarClienteMenuOption(new CadastrarClienteUseCase(clienteRepository), scanner),
            new ListarProdutosMenuOption(new ListarProdutosUseCase(produtoRepository)),
            new ListarServicosMenuOption(new ListarServicosUseCase(servicoRepository)),
            new ListarClientesMenuOption(new ListarClientesUseCase(clienteRepository)),
            new CriarOrcamentoMenuOption(
                new CriarOrcamentoUseCase(orcamentoRepository),
                new ListarClientesUseCase(clienteRepository),
                new AdicionarItemOrcamentoUseCase(orcamentoRepository),
                scanner,
                clienteRepository,
                produtoRepository,
                servicoRepository
            ),
            new ListarOrcamentosMenuOption(new ListarOrcamentosUseCase(orcamentoRepository)),
            new AprovarOrcamentoMenuOption(
                new AprovarOrcamentoUseCase(orcamentoRepository),
                new ListarOrcamentosUseCase(orcamentoRepository),
                scanner
            ),
            new GerarPedidoMenuOption(
                new GerarPedidoUseCase(pedidoRepository),
                new ListarOrcamentosUseCase(orcamentoRepository),
                scanner
            ),
            new ListarPedidosMenuOption(new ListarPedidosUseCase(pedidoRepository)),
            new ConfirmarPedidoMenuOption(
                new ConfirmarPedidoUseCase(pedidoRepository),
                new ListarPedidosUseCase(pedidoRepository),
                scanner
            ),
            new GerarFaturaMenuOption(
                new GerarFaturaUseCase(pedidoRepository),
                new ListarPedidosUseCase(pedidoRepository),
                faturaService,
                faturaRepository,
                scanner
            ),
            new ListarFaturasMenuOption(faturaRepository)
        );
        
        // Criação e execução do menu
        Menu menu = new MenuPrincipal(menuOptions, scanner);
        menu.executar();
        
        scanner.close();
    }
}