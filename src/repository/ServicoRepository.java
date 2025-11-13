package repository;

import model.Servico;
import java.util.Optional;
import java.util.List;

public interface ServicoRepository extends Repository<Servico, String> {
    Optional<Servico> buscarPorCodigo(String codigo);
    List<Servico> buscarPorDescricao(String descricao);
}