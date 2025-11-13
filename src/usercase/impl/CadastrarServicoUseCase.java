package usecase.impl;

import model.Servico;
import repository.ServicoRepository;
import usecase.UseCase;

public class CadastrarServicoUseCase implements UseCase<CadastrarServicoUseCase.Input, Servico> {
    
    private final ServicoRepository servicoRepository;
    
    public CadastrarServicoUseCase(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }
    
    @Override
    public Servico execute(Input input) {
        Servico servico = new Servico(input.codigo(), input.descricao(), input.preco(), input.duracaoHoras());
        return servicoRepository.salvar(servico);
    }
    
    public record Input(String codigo, String descricao, double preco, int duracaoHoras) {}
}