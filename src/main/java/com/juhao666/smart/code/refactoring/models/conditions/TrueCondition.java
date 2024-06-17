package com.juhao666.smart.code.refactoring.models.conditions;

import com.juhao666.smart.code.refactoring.models.Condition;

public class TrueCondition implements Condition {

    @Override
    public String getDescription() {
        return "Makes a recipe/action always applicable";
    }

//    @Override
//    public boolean evaluate(ProjectContext context) {
//        return true;
//    }
}
