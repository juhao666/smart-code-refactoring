package com.juhao666.smart.code.refactoring.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
//import org.springframework.sbm.engine.context.ProjectContext;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    private String name;

    private String description;

    private Condition condition = Condition.TRUE;

    @JsonProperty(value = "actions", required = true)
    @Valid
    @NotEmpty
    @Singular
    private List<Action> actions;

    private Integer order = 0;

    @JsonCreator
    public Recipe(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "actions", required = false) List<Action> actions,
            @JsonProperty(value = "condition") Condition condition,
            @JsonProperty(value = "order") Integer order
    ) {
        this.name = name;
        this.actions = actions;
        this.condition = condition == null ? Condition.TRUE : condition;
        this.order = order == null ? 0 : order;
    }

    public Recipe(
            String name,
            List<Action> actions,
            Condition condition
    ) {
        this.name = name;
        this.actions = actions;
        this.condition = condition == null ? Condition.TRUE : condition;
        this.order = 0;
    }

    public Recipe(
            String name,
            String description,
            Condition condition,
            List<Action> actions
    ) {
        this.name = name;
        this.description = description;
        this.actions = actions;
        this.condition = condition == null ? Condition.TRUE : condition;
    }


    public Recipe(String name, List<Action> actions) {
        this(name, actions, Condition.TRUE);
    }



    public List<Action> apply() {
        List<Action> appliedActions = new ArrayList<>();
        for(Action action:actions){
            if (action.isApplicable()) {
                appliedActions.add(action);
            }
        }
        return appliedActions;
    }

    public String getDetails() {
        // FIXME: prints all actions, even so they were not applicable because the condition was false
        String conditionDescriptions = actions.stream()
                .map(a -> a.getCondition())
                .map(c -> "\t* " + c.getDescription() + "\n")
                .collect(Collectors.joining());

        String actionDescriptions = actions.stream()
                .map(a -> "\t* " + a.getDetailedDescription() + "\n")
                .collect(Collectors.joining());

        return this
                + "\nThis recipe is applicable to an app if:\n"
                + conditionDescriptions
                + "The recipe requires these actions to be performed\n"
                + actionDescriptions;
    }

    public String toString() {
        return toString("");
    }

    public String toString(String prefix) {
        return new StringBuilder()
                .append(prefix == null ? "" : prefix)
                .append(name)
                .append(" - ")
                .append(null == description ? "<no description>" : description)
                .toString();
    }


}
