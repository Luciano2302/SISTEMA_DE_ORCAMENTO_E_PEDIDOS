package usecase;

public interface UseCase<T, R> {
    R execute(T input);
}