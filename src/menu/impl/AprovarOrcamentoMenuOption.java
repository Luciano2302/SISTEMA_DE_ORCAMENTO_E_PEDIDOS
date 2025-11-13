package menu.impl;

import menu.MenuOption;
import usecase.impl.AprovarOrcamentoUseCase;
import usecase.impl.ListarOrcamentosUseCase;
import java.util.Scanner;

public class AprovarOrcamentoMenuOption implements MenuOption {

    private final AprovarOrcamentoUseCase useCase;
    private final ListarOrcamentosUseCase listarOrcamentosUseCase;
    private final Scanner scanner;

    public AprovarOrcamentoMenuOption(
        AprovarOrcamentoUseCase useCase,
        ListarOrcamentosUseCase listarOrcamentosUseCase,
        Scanner scanner
    ) {
        this.useCase = useCase;
        this.listarOrcamentosUseCase = listarOrcamentosUseCase;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("\n=== APROVAR ORÇAMENTO ===");

        var orcamentos = listarOrcamentosUseCase.execute(null);
        if (orcamentos.isEmpty()) {
            System.out.println("Nenhum orçamento para aprovar!");
            return;
        }

        var orcamentosParaAprovar = orcamentos.stream()
            .filter(o -> o.getStatus().name().equals("AGUARDANDO_APROVACAO"))
            .toList();

        if (orcamentosParaAprovar.isEmpty()) {
            System.out.println("Nenhum orçamento aguardando aprovação.");
            return;
        }

        for (var orcamento: orcamentosParaAprovar) {
            System.out.printf("Orçamento #%d | Cliente: %s | Total: R$ %.2f\n",
                orcamento.getNumero(), orcamento.getCliente().getNome(),
                orcamento.calcularValorTotal());
        }

        int numero = lerInteiro("Número do orçamento para aprovar: ");

        var orcamentoSelecionado = orcamentosParaAprovar.stream()
            .filter(o -> o.getNumero() == numero)
            .findFirst();

        if (orcamentoSelecionado.isPresent()) {
            try {
                useCase.execute(orcamentoSelecionado.get());
                System.out.println("✅ Orçamento #" + numero + " aprovado com sucesso!");
            } catch (Exception e) {
                System.out.println("❌ Erro ao aprovar orçamento: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Orçamento #" + numero + " não encontrado ou não está aguardando aprovação!");
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
        return "Aprovar Orçamento";
    }

    @Override
    public int getOrder() {
        return 9;
    }
}