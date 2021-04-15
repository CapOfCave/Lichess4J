package me.kecker.lichess4j.test.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public final class FileReaderUtil {

    /**
     * Reads a file and returns its content in a buffer, using the passed Class's
     * class loader.
     * 
     * @param fileName  Name of the file to be read
     * @param baseClass Class whose class loader will be used
     * @return The content of the input file as a single String
     * @throws IOException
     */
    public static String readFromFile(String fileName, Class<?> baseClass) throws IOException {
        return new BufferedReader(new InputStreamReader(baseClass.getResourceAsStream(fileName)))
                .lines()
                .collect(Collectors.joining("\r\n"));

    }
    
    private FileReaderUtil() {
        // this class should not be instantiated
    }
}
