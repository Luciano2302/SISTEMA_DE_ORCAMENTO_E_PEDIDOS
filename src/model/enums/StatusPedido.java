package model.enums;

public enum StatusPedido implements Status {
    ABERTO {
        @Override
        public boolean canTransitionTo(Status nextStatus) {
            return nextStatus == CONFIRMADO || nextStatus == CANCELADO;
        }
    },
    CONFIRMADO {
        @Override
        public boolean canTransitionTo(Status nextStatus) {
            return nextStatus == FATURADO || nextStatus == CANCELADO;
        }
    },
    FATURADO {
        @Override
        public boolean canTransitionTo(Status nextStatus) {
            return nextStatus == ENTREGUE;
        }
    },
    ENTREGUE {
        @Override
        public boolean canTransitionTo(Status nextStatus) {
            return false;
        }
    },
    CANCELADO {
        @Override
        public boolean canTransitionTo(Status nextStatus) {
            return false;
        }
    };
}