package usecase.impl;

import model.Cliente;
import model.Orcamento;
import repository.OrcamentoRepository;
import usecase.UseCase;

public class CriarOrcamentoUseCase implements UseCase<Cliente, Orcamento> {
    
    private final OrcamentoRepository orcamentoRepository;
    
    public CriarOrcamentoUseCase(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }
    
    @Override
    public Orcamento execute(Cliente cliente) {
        Orcamento orcamento = new Orcamento(cliente);
        return orcamentoRepository.salvar(orcamento);
    }
}