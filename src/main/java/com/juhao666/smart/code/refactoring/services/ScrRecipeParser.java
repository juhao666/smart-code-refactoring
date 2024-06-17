package com.juhao666.smart.code.refactoring.services;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.juhao666.smart.code.refactoring.models.Recipe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Slf4j
public class ScrRecipeParser {

    private final Yaml yaml;

    private final YAMLMapper yamlMapper;

    private final ScrRecipeHelper scrRecipeHelper;

    private final List<Recipe> recipeBeans;

    public List<Recipe> parse(Resource[] files) {
        List<Recipe> recipeList = Arrays.stream(files)
                .peek(f -> log.debug("loading Recipe " + f.toString()))
                .flatMap(f -> Arrays.stream(parseRecipe(f)))
                .collect(Collectors.toList());
        // Add recipes defined as Spring beans
        recipeList.addAll(recipeBeans);
        return recipeList;
    }

    private Recipe[] parseRecipe(Resource resource) {
        Assert.notNull(resource, "the recipe definition cannot be empty!");
        try {
            String recipeYaml = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return yamlMapper.readValue(recipeYaml, Recipe[].class);
        } catch (IOException e) {
            throw new RuntimeException("Error loading recipe from resource: " + resource.getFilename(), e);
        }
//        Assert.notNull(resource, "the recipe definition cannot be empty!");
//        try {
//            String resourceString = scrRecipeHelper.getResourceAsString(resource);
//            return parseRecipe(resourceString);
//            //return parseRecipe(recipeDefinition.getInputStream());
//        } catch (IOException e) {
//            throw new IllegalArgumentException("Can't read recipes from resource '" + resource.getFilename() + "'", e);
//        }
    }

    private Recipe[] parseRecipe(InputStream recipeInputStream) throws IOException {
        Recipe[] recipes;
        recipes = yaml.loadAs(recipeInputStream, Recipe[].class);
        return recipes;
    }

    private Recipe[] parseRecipe(String recipesStr) throws IOException {
        Recipe[] recipes;
        recipes = yamlMapper.readValue(recipesStr, Recipe[].class);
        return recipes;
    }
}
