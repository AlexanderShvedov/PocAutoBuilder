package modules.parsers;

import utils.ParsedFileInfo;

import java.io.File;

public interface InputFileParserInterface {
    public ParsedFileInfo parse(String filePath);
}
