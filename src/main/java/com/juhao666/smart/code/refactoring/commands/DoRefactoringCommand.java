package com.juhao666.smart.code.refactoring.commands;

import com.juhao666.smart.code.refactoring.models.Recipe;
import com.juhao666.smart.code.refactoring.services.SrcRecipesLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Optional;

//@ShellComponent
@Slf4j
public class DoRefactoringCommand {

    @Value("${rewrite.recipe.dir}")
    private String recipeDir;

    @Autowired
    SrcRecipesLoader srcRecipesLoader;

    @ShellMethod(key = "scr", value = "smart code refactoring")
    public void doRefactor(
            @ShellOption(value = {"-r", "--recipes"}, defaultValue = "java_8_17") String recipeDir,
            @ShellOption(value = {"-i", "--input"}) String codeDir) {
//        try {
        //using recipe loader to load all the recipe yml files under specified folder
        //using yml parser to parse all the yml to Recipes object
        List<Recipe> recipes = srcRecipesLoader.load(recipeDir);
        log.info("recipe size is {}", Optional.of(recipes.size()));
        //apply each recipe to all the source codes under specified folder
        for (Recipe recipe : recipes) {
            recipe.apply();
        }

//            Path inputPath = Paths.get(recipeDir);
//            Path outputPath = Paths.get(codeDir);
//
//            JavaParser javaParser = JavaParser.fromJavaVersion()
//                    .classpath("spring-boot-autoconfigure")
//                    .build();
//
//            YamlParser yamlParser = new YamlParser();
//
//            List<J.CompilationUnit> sourceFiles = new ArrayList<>();
//            Files.walk(inputPath)
//                    .filter(Files::isRegularFile)
//                    .filter(p -> p.toString().endsWith(".java"))
//                    .forEach(path -> {
//                        try {
//                            sourceFiles.add(javaParser.parse(Arrays.toString(Files.readAllBytes(path))).get(0));
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//
//            List<Recipe> recipes = new ArrayList<>();
//            File[] recipeFiles = new File(recipeDir).listFiles((dir, name) -> name.endsWith(".yml"));
//            if (recipeFiles != null) {
//                for (File recipeFile : recipeFiles) {
//                    boolean addSuccess = recipes.addAll(yamlParser.parseInputs(recipeFile));
//                }
//            }
//
//            Consumer<J.CompilationUnit> visitor = recipes.stream()
//                    .map(recipe -> recipe.getVisitor(J.CompilationUnit.class))
//                    .reduce(Consumer::andThen)
//                    .orElse(c -> {});
//
//            for (J.CompilationUnit compilationUnit : sourceFiles) {
//                visitor.accept(compilationUnit);
//            }
//
//            JavaParser.Fix fix = javaParser.fix(sourceFiles);
//            fix.doNext(outputPath);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }


    @ShellMethod(key = {"help scr", "scr help", "scr -help", "scr --help", "scr /?"}, value = "Show help for the 'scr' command")
    public void scrHelp() {
        System.out.println("Usage: scr -i <input-dir> -o <output-dir>");
        System.out.println("Options:");
        System.out.println("  -i, --input\tInput directory");
        System.out.println("  -o, --output\tOutput directory");
    }
}


