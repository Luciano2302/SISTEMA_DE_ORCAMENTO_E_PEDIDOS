package model.enums;

public interface Status {
    String name();
    boolean canTransitionTo(Status nextStatus);
}