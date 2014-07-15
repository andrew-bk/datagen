package org.ak.datagen.format;

import org.ak.datagen.data.Datum;
import org.ak.datagen.structure.TabularDataStructure;

import java.util.Optional;

import static java.util.stream.Collectors.joining;

/**
 */
public class CSVFormatter {

    private TabularDataStructure tabularDataStructure;
    private int numLinesFormatted = 0;
    private boolean printColumnHeadings;

    public CSVFormatter(TabularDataStructure tabularDataStructure) {
        if(tabularDataStructure == null) {
            throw new IllegalArgumentException("tabularDataStructure is a required attribute");
        }
        this.tabularDataStructure = tabularDataStructure;
        this.printColumnHeadings = true;
    }

    public void printColumnHeadings(boolean printColumnHeadings) {
        this.printColumnHeadings = printColumnHeadings;
    }

    public boolean hasNext() {
        return numLinesFormatted < tabularDataStructure.getNumberOfRows() + (printColumnHeadings ? 1 : 0);
    }

    public String getNextLine() {
        String nextLine = "";

        if(hasNext()) {
            if(printColumnHeadings && numLinesFormatted == 0) {
                nextLine = tabularDataStructure.getRowData()
                        .stream()
                        .map(Datum::getName)
                        .collect(joining(",", "", ","));
            } else {
                nextLine = tabularDataStructure.getRowData()
                        .stream()
                        .map(datum -> datum.generate().toString())
                        .collect(joining(",", "", ","));
            }
            numLinesFormatted++;
        }

        return nextLine;
    }

}
