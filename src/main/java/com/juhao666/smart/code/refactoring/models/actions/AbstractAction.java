package com.juhao666.smart.code.refactoring.models.actions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juhao666.smart.code.refactoring.models.Action;
import com.juhao666.smart.code.refactoring.models.Condition;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.sbm.engine.context.ProjectContext;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractAction implements Action {

    @NotBlank
    private String description;

    private Condition condition = Condition.TRUE;

    @Autowired
    @JsonIgnore
    @Getter
    private ApplicationEventPublisher eventPublisher;

    @Override
    public String getDetailedDescription() {
        final String conditionDescription =
                (getCondition().getDescription() == null || getCondition().getDescription().isBlank())
                        ? ""
                        : getCondition().getDescription() + " then\n\t  ";
        return conditionDescription + getDescription();
    }

//    @Override
//    public void applyInternal(ProjectContext context) {
//        apply(context);
//
//    }

    @Override
    public boolean isAutomated() {
        return false;
    }
}
