package repository;

import model.Orcamento;
import java.util.Optional;
import java.util.List;


public interface OrcamentoRepository extends Repository<Orcamento, String> {
    Optional<Orcamento> buscarPorNumero(int numero);
    List<Orcamento> buscarPorCliente(String clienteId);
}