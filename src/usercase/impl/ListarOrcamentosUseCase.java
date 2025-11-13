package usecase.impl;

import model.Orcamento;
import repository.OrcamentoRepository;
import usecase.UseCase;
import java.util.List;

public class ListarOrcamentosUseCase implements UseCase<Void, List<Orcamento>> {
    
    private final OrcamentoRepository orcamentoRepository;
    
    public ListarOrcamentosUseCase(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }
    
    @Override
    public List<Orcamento> execute(Void input) {
        return orcamentoRepository.listarTodos();
    }
}