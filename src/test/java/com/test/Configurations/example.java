package com.test.Configurations;

import java.nio.file.Path;
import java.nio.file.Paths;

public class example {

    public static void main(String[] args) {

        Path currentDir = Paths.get("");
        System.out.println(currentDir.toAbsolutePath());
        System.out.println(System.getProperty("user.dir"));
    }
}