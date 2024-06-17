package com.juhao666.smart.code.refactoring.models;

//import org.springframework.sbm.engine.context.ProjectContext;

public interface Condition {

    Condition FALSE = new Condition() {

        @Override
        public String getDescription() {
            return "FALSE";
        }

//        @Override
//        public boolean evaluate(ProjectContext context) {
//            return false;
//        }
    };
    Condition TRUE = new Condition() {

        @Override
        public String getDescription() {
            return "TRUE";
        }

//        @Override
//        public boolean evaluate(ProjectContext context) {
//            return true;
//        }
    };

    default Condition or(Condition other) {
        return new Condition() {

            @Override
            public String getDescription() {
                if (Condition.this.getDescription() == null) {
                    return other.getDescription();
                } else if (other.getDescription() == null) {
                    return Condition.this.getDescription();
                } else {
                    return Condition.this.getDescription() + " or " + other.getDescription();
                }
            }

            @Override
            public boolean evaluate() {
                return true;
            }
        };
    }

    String getDescription();

    //boolean evaluate(ProjectContext context);
    default boolean evaluate(){return true;}
}
