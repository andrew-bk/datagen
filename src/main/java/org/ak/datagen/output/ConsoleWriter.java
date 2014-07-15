package org.ak.datagen.output;

import org.ak.datagen.format.CSVFormatter;
import org.ak.datagen.format.JSONFormatter;
import org.ak.datagen.format.XMLFormatter;

import java.io.PrintStream;

/**
 */
public class ConsoleWriter {

    private PrintStream console;

    public ConsoleWriter() {
        this.console = System.out;
    }

    public void write(CSVFormatter formatter) {
        while(formatter.hasNext()) {
            console.println(formatter.getNextLine());
        }
    }

    public void write(XMLFormatter formatter) {

    }

    public void write(JSONFormatter formatter) {

    }

    protected PrintStream getConsole() {
        return this.console;
    }

    protected void setConsole(PrintStream console) {
        this.console = console;
    }
}
