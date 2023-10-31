package utils;

import java.util.HashMap;

public class ParsedFileInfo {
    private HashMap<String, String> files;
    private String poc;

    public HashMap<String, String> getFiles() {
        return files;
    }

    public String getPoc() {
        return poc;
    }

    public String getLibName() {
        return libName;
    }

    public String getVersion() {
        return version;
    }

    private String libName;
    private String version;

    public ParsedFileInfo(HashMap<String, String> files, String libName, String poc, String version) {
        this.files = files;
        this.poc = poc;
        this.libName = libName;
        this.version = version;
    }
}
