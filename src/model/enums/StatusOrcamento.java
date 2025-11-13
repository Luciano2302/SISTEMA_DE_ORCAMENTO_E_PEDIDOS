package model.enums;

public enum StatusOrcamento implements Status {
    RASCUNHO {
        @Override
        public boolean canTransitionTo(Status nextStatus) {
            return nextStatus == AGUARDANDO_APROVACAO || nextStatus == CANCELADO;
        }
    },
    AGUARDANDO_APROVACAO {
        @Override
        public boolean canTransitionTo(Status nextStatus) {
            return nextStatus == APROVADO || nextStatus == REJEITADO || nextStatus == CANCELADO;
        }
    },
    APROVADO {
        @Override
        public boolean canTransitionTo(Status nextStatus) {
            return nextStatus == CANCELADO;
        }
    },
    REJEITADO {
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