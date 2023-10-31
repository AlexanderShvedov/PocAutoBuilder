package modules.parsers;

import utils.ParsedFileInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputSimpleParser implements InputFileParserInterface{
    @Override
    public ParsedFileInfo parse(String filePath) {
        try {
            String javaContent = new String(Files.readAllBytes(Paths.get(filePath + "/exp.java")));
            String nameContent = new String(Files.readAllBytes(Paths.get(filePath + "/libName.txt")));
            String versionContent = new String(Files.readAllBytes(Paths.get(filePath + "/libVersion.txt")));
            return new ParsedFileInfo(null, nameContent, javaContent, versionContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
