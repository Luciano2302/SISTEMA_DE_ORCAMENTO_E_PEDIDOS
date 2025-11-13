package model.enums;

public enum StatusFatura implements Status {
    ABERTA {
        @Override
        public boolean canTransitionTo(Status nextStatus) {
            return nextStatus == PAGA || nextStatus == CANCELADA || nextStatus == ATRASADA;
        }
    },
    PAGA {
        @Override
        public boolean canTransitionTo(Status nextStatus) {
            return false;
        }
    },
    CANCELADA {
        @Override
        public boolean canTransitionTo(Status nextStatus) {
            return false;
        }
    },
    ATRASADA {
        @Override
        public boolean canTransitionTo(Status nextStatus) {
            return nextStatus == PAGA || nextStatus == CANCELADA;
        }
    };
}