package usecase.impl;

import model.Servico;
import repository.ServicoRepository;
import usecase.UseCase;
import java.util.List;

public class ListarServicosUseCase implements UseCase<Void, List<Servico>> {
    
    private final ServicoRepository servicoRepository;
    
    public ListarServicosUseCase(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }
    
    @Override
    public List<Servico> execute(Void input) {
        return servicoRepository.listarTodos();
    }
}