package repository;

import model.Fatura;
import java.util.Optional;

public interface FaturaRepository extends Repository<Fatura, String> {
    Optional<Fatura> buscarPorNumero(int numero);
    Optional<Fatura> buscarPorPedido(int pedidoNumero);
}