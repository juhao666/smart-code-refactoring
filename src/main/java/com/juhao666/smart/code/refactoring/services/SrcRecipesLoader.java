package com.juhao666.smart.code.refactoring.services;

import com.juhao666.smart.code.refactoring.models.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SrcRecipesLoader {

    private final ScrRecipeParser scrRecipeParser;
    private final ResourceLoader resourceLoader;


    public List<Recipe> load(String path){
        path = "classpath:/java_8_17/*.yml";
        Resource[] files = loadResources(path);
        return scrRecipeParser.parse(files);
    }

    private Resource[] loadResources(String path) {
        try {
            return ResourcePatternUtils.getResourcePatternResolver(null).getResources(path);
            //return ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(path);
        } catch (IOException e) {
            throw new RuntimeException("No recipe definition file found under folder '" + path + "'", e);
        }
    }

}
