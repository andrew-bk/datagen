package org.ak.datagen.output;


import org.ak.datagen.format.CSVFormatter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 */
public class FileWriter  {

    public FileWriter() {

    }

    public void write(Path path, CSVFormatter formatter) throws IOException {
        write(Files.newBufferedWriter(path), formatter);
    }

    /**
     * For testing purposes.
     *
     * @param writer
     * @param formatter
     * @throws IOException
     */
    void write(Writer writer, CSVFormatter formatter) throws IOException {
        while(formatter.hasNext())
            writer.write(formatter.getNextLine() + System.getProperty("line.separator"));
    }
}
