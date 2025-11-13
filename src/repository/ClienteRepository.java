package repository;

import model.Cliente;
import java.util.Optional;
import java.util.List;

public interface ClienteRepository extends Repository<Cliente, String> {
    Optional<Cliente> buscarPorEmail(String email);
    List<Cliente> buscarPorNome(String nome);
}