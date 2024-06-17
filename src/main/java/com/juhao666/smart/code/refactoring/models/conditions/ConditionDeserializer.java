package com.juhao666.smart.code.refactoring.models.conditions;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.juhao666.smart.code.refactoring.models.Condition;
import com.juhao666.smart.code.refactoring.models.conditions.TrueCondition;

import java.io.IOException;

public class ConditionDeserializer extends StdDeserializer<Condition> {

    public ConditionDeserializer() {
        this(null);
    }

    protected ConditionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Condition deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        switch (type) {
            case "com.juhao666.smart.code.refactoring.models.conditions.TrueCondition":
                return new TrueCondition();
            // Add more condition types here as needed
            default:
                throw new IllegalArgumentException("Unknown condition type: " + type);
        }
    }
}
