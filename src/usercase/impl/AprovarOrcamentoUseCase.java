package usecase.impl;

import model.Orcamento;
import repository.OrcamentoRepository;
import usecase.VoidUseCase;

public class AprovarOrcamentoUseCase implements VoidUseCase<Orcamento> {
    
    private final OrcamentoRepository orcamentoRepository;
    
    public AprovarOrcamentoUseCase(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }
    
    @Override
    public void execute(Orcamento orcamento) {
        orcamento.aprovar();
        orcamentoRepository.salvar(orcamento);
    }
}