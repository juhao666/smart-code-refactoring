package com.juhao666.smart.code.refactoring.commands;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ShellComponent
public class RefactoringCommands {

    @ShellMethod(value = "Smart Code Refactoring", key = "scr")
    public String doRefactor(
            @ShellOption(value = {"-f", "--force"}, defaultValue = "true") boolean force,
            @ShellOption(value = {"-d", "--dry-run"}, defaultValue = "false") boolean dryRun,
            @ShellOption(value = {"--target"}, defaultValue = "") String target,
            @ShellOption(value = {"--source"}, defaultValue = "") String source,
            @ShellOption(value = {"--refactor"}, defaultValue = "") String refactor) {
        if (source.isBlank() || refactor.isBlank()) {
            return help();
        }
        if (target.isBlank()) {
            target = source;
        }
        // todo Perform refactoring logic here
        return "Refactoring completed for source code at: " + source
                + " to target folder: " + target
                + " with refactoring type: " + refactor
                + " options force as: " + force
                + " options dryRun as: " + dryRun;
    }

    @ShellMethod(value = "Show help", key = {"help scr", "scr help", "scr -help", "scr /?"})
    public String help() {
        String v = getVersion();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = dateFormat.format(new Date());
        return "Smart Code Refactoring Release " + v + " - Production on " + now + "\n"
                +
                """
                                        
                        Usage: scr [options] <source_code_path> <target_folder_path> <refactor_type>
                                        
                        Options:
                            -h, --help        Show help
                            -v, --version     Show version
                            -f, --force       Force refactoring without confirmation
                            -d, --dry-run     Show refactoring plan without executing
                            
                        Arguments:
                            --source <source_code_path>  Path to the source code to be refactored
                            [--target <target_folder_path>]  Path to the output folder for the refactored code (default: same as source)
                            --refactor <refactor_type>  Type of refactoring to perform
                                      
                        Examples:
                            scr -f --source /path/to/spring-boot-app --refactor java8-to-java11
                            scr -f --source /path/to/spring-boot-app --target /output/path --refactor java8-to-java11
                            scr --source /path/to/spring-boot-app --target /output/path --refactor spring2-to-spring3
                        """;
    }

    @ShellMethod(value = "Show version", key = {"scr -v", "scr --version"})
    public String version() {
        String v = getVersion();
        return "Smart Code Refactoring version: " + v;
    }

    public static String getVersion() {
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader("pom.xml"));
            return model.getVersion();
        } catch (IOException | XmlPullParserException e) {
            return "Version not found";
        }
    }
}