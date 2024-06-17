package com.juhao666.smart.code.refactoring.configuration;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.juhao666.smart.code.refactoring.models.Action;
import com.juhao666.smart.code.refactoring.models.Condition;
import com.juhao666.smart.code.refactoring.models.actions.ActionDeserializer;
import com.juhao666.smart.code.refactoring.models.conditions.ConditionDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

@Configuration
public class ScrConfiguration {

    @Bean
    YAMLMapper yamlObjectMapper() {
        YAMLMapper yamlMapper = new YAMLMapper(new YAMLFactory());
        SimpleModule conditionModule = new SimpleModule();
        conditionModule.addDeserializer(Condition.class, new ConditionDeserializer());
        yamlMapper.registerModule(conditionModule);

        SimpleModule actionModule = new SimpleModule();
        actionModule.addDeserializer(Action.class, new ActionDeserializer());
        yamlMapper.registerModule(actionModule);

        return yamlMapper;
    }

    @Bean
    Yaml yaml() {
        Yaml yaml = new Yaml();
        return yaml;
    }

}
