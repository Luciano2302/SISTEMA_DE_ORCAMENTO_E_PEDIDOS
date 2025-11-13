package model;

import model.enums.Status;
import java.time.LocalDateTime;

public abstract class AbstractStatusEntity<T extends Status> extends BaseEntity {
    protected T status;

    protected AbstractStatusEntity(T initialStatus) {
        super();
        this.status = initialStatus;
    }

    public void changeStatus(T newStatus) {
        if (status.canTransitionTo(newStatus)) {
            this.status = newStatus;
            setUpdatedAt(LocalDateTime.now());
        } else {
            throw new IllegalStateException(
                String.format("Transição de status inválida: %s -> %s", status, newStatus)
            );
        }
    }

    public T getCurrentStatus() {
        return status;
    }

    public boolean canChangeTo(T newStatus) {
        return status.canTransitionTo(newStatus);
    }
}