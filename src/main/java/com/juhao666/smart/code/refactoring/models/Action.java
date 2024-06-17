package com.juhao666.smart.code.refactoring.models;

import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.sbm.engine.context.ProjectContext;

public interface Action {
    String getDescription();

    String getDetailedDescription();

    Condition getCondition();

    //void apply(ProjectContext context);
    void apply();

//    default void applyWithStatusEvent(ProjectContext context) {
//        ApplicationEventPublisher eventPublisher = getEventPublisher();
//        if (eventPublisher != null) {
//            eventPublisher.publishEvent(new ActionStartedEvent(getDescription()));
//        }
//        try {
//            applyInternal(context);
//        } catch(Exception e) {
//            String message = "Action ["+this.getClass().getSimpleName()+"] '" + this.getDescription() + "' failed: " + e.getMessage();
//            if (eventPublisher != null) {
//                eventPublisher.publishEvent(new ActionFailedEvent(message));
//            }
//            throw new ActionFailedException(message, e);
//        }
//        if (eventPublisher != null) {
//            eventPublisher.publishEvent(new ActionFinishedEvent(getDescription()));
//        }
//    }
//
//    void applyInternal(ProjectContext context);

    ApplicationEventPublisher getEventPublisher();

    boolean isAutomated();


//    default boolean isApplicable(ProjectContext context) {
//        return getCondition().evaluate(context);
//    }
    default boolean isApplicable() {
        return true;
    }

    /**
     * Informs the user about a long running process.
     * Every started process must be ended with {@code #endProcess}.
     */
//    default void startProcess(String name) {
//        getEventPublisher().publishEvent(new ActionProcessStartedEvent(name));
//    }

    /**
     * Ends a previously started process.
     */
//    default void endProcess() {
//        getEventPublisher().publishEvent(new ActionProcessFinishedEvent());
//    }

    /**
     * Logs an event to the user as part of the currently active process.
     */
//    default void logEvent(String s) {
//        getEventPublisher().publishEvent(new ActionLogEvent(s));
//    }
}
