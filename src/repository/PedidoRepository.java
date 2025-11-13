package repository;

import model.Pedido;
import java.util.Optional;
import java.util.List;


public interface PedidoRepository extends Repository<Pedido, String> {
    Optional<Pedido> buscarPorNumero(int numero);
    List<Pedido> buscarPorOrcamento(int orcamentoNumero);
}