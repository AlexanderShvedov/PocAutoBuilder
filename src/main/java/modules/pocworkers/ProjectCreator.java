package modules.pocworkers;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.shared.invoker.*;
import utils.ParsedFileInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ProjectCreator {
    public void create(ParsedFileInfo info, String path, List<String> deps) throws IOException, MavenInvocationException, MojoExecutionException {
        Files.createDirectories(Paths.get("../PocAutoBuilder/output/1/src/main/java/com/example/"));

        File javaFile = new File("../PocAutoBuilder/output/1/src/main/java/com/example/Main.java");
        javaFile.createNewFile();
        FileOutputStream javaStream = new FileOutputStream(javaFile,false);
        byte[] buffer = info.getPoc().getBytes();
        javaStream.write(buffer);
        File pomFile = new File("../PocAutoBuilder/output/1/pom.xml");
        javaFile.createNewFile();

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File("/usr/local/opt/maven@3.9/"));
        for (String dep : deps) {
            createPom(info, pomFile, dep);
            InvocationRequest requestForBuild = new DefaultInvocationRequest();
            requestForBuild.setPomFile(pomFile);
            requestForBuild.setGoals(List.of("clean install"));
            if (invoker.execute( requestForBuild ).getExitCode() == 0) {
                break;
            }
        }

        InvocationRequest requestForExec = new DefaultInvocationRequest();
        requestForExec.setPomFile(pomFile);
        requestForExec.setGoals(List.of("exec:java -Dexec.mainClass=\"com.example.Main\""));
        invoker.execute(requestForExec);

//        Runtime.getRuntime().exec("mvn clean install", null, new File("/Users/aleksandrsvedov/PocAutoBuilder/output/1/"));
//        Runtime.getRuntime().exec("mvn exec:java -Dexec.mainClass=\"com.example.Main\"", null, new File("/Users/aleksandrsvedov/PocAutoBuilder/output/1/"));
    }

    private void createPom(ParsedFileInfo info, File pomFile, String dep) throws IOException {
        FileOutputStream pomStream = new FileOutputStream(pomFile,false);
        byte[]buffer = pomContent(info, dep).getBytes();
        pomStream.write(buffer);
    }
    private String pomContent(ParsedFileInfo info, String dep) {
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <groupId>id</groupId>\n" +
                "    <artifactId>id</artifactId>\n" +
                "    <version>1.0-SNAPSHOT</version>\n" +
                "\n" +
                "    <properties>\n" +
                "        <maven.compiler.source>11</maven.compiler.source>\n" +
                "        <maven.compiler.target>11</maven.compiler.target>\n" +
                "        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n" +
                "    </properties>" +
                "    <dependencies>\n" +
                "        <dependency>\n" +
                "            <groupId>" + dep +"</groupId>\n" +
                "            <artifactId>" + info.getLibName() + "</artifactId>\n" +
                "            <version>" + info.getVersion() + "</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>javax.ejb</groupId>\n" +
                "            <artifactId>javax.ejb-api</artifactId>\n" +
                "            <version>3.2.2</version>\n" +
                "        </dependency>" +
                "    </dependencies>\n" +
                "\n" +
                "</project>";
    }
}
