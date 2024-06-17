package com.juhao666.smart.code.refactoring.models.actions;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.juhao666.smart.code.refactoring.models.Action;
import com.juhao666.smart.code.refactoring.models.actions.SetPropertyAction;

import java.io.IOException;

public class ActionDeserializer extends StdDeserializer<Action> {

    public ActionDeserializer() {
        this(null);
    }

    protected ActionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Action deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        switch (type) {
            case "com.juhao666.smart.code.refactoring.models.actions.SetPropertyAction":
                return new SetPropertyAction();
            // Add more action types here as needed
            default:
                throw new IllegalArgumentException("Unknown action type: " + type);
        }
    }
}
