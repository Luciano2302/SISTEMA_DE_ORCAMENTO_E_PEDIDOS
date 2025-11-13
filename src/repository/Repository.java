package repository;

import java.util.List;
import java.util.Optional;
import java.util.List;

public interface Repository<T, ID> {
    T salvar(T entity);
    Optional<T> buscarPorId(ID id);
    List<T> listarTodos();
    void remover(ID id);
}