package model;

import java.time.LocalDateTime;

public interface Entity extends Identifiable {
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    void setUpdatedAt(LocalDateTime updatedAt);
}