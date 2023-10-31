import modules.parsers.InputSimpleParser;
import modules.pocworkers.ProjectCreator;
import modules.xmlworkers.SimpleDependencyJsonParser;
import modules.xmlworkers.SimpleUrlCreater;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.shared.invoker.MavenInvocationException;
import utils.ParsedFileInfo;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, MavenInvocationException, MojoExecutionException {
        if (args.length == 0) {
            throw new IOException("please, enter folder with tests");
        }
        String path = args[0];
        ParsedFileInfo info = new InputSimpleParser().parse(path);
        SimpleUrlCreater a = new SimpleUrlCreater();
        SimpleDependencyJsonParser b = new SimpleDependencyJsonParser();
        List<String> deps = b.getDependency(a.getJson(info.getLibName()));
        ProjectCreator creator = new ProjectCreator();
        creator.create(info, path, deps);
    }
}