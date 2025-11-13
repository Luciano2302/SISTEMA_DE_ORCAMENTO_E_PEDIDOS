package usecase.impl;

import model.ItemVendavel;
import model.Orcamento;
import repository.OrcamentoRepository;
import usecase.VoidUseCase;

public class AdicionarItemOrcamentoUseCase implements VoidUseCase<AdicionarItemOrcamentoUseCase.Input> {
    
    private final OrcamentoRepository orcamentoRepository;
    
    public AdicionarItemOrcamentoUseCase(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }
    
    @Override
    public void execute(Input input) {
        Orcamento orcamento = input.orcamento();
        ItemVendavel item = input.item();
        int quantidade = input.quantidade();
        
        orcamento.adicionarItem(item, quantidade);
        orcamentoRepository.salvar(orcamento);
    }
    
    public record Input(Orcamento orcamento, ItemVendavel item, int quantidade) {}
}