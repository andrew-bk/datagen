package org.ak.datagen.structure;

import org.ak.datagen.data.Datum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public class TabularDataStructure {

    private List<Datum> rowData;
    private int numberOfRows;

    private TabularDataStructure(List<Datum> rowData, int numberOfRows) {
        if(rowData == null) {
            throw new IllegalArgumentException("Row data is required for a TabularDataStructure");
        }
        if(rowData.isEmpty()) {
            throw new IllegalArgumentException("Row data must not be empty");
        }
        this.rowData = rowData;
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    public List<Datum> getRowData() {
        return this.rowData;
    }

    public static class TabularDataStructureBuilder {

        private List<Datum> rowData;
        private int numberOfRows;

        public TabularDataStructureBuilder() {
            this.numberOfRows = 1;
        }

        public TabularDataStructureBuilder withRow(Datum... data) {
            rowData = Arrays.asList(data);
            return this;
        }

        public TabularDataStructureBuilder numberOfRows(int numberOfRows) {
            this.numberOfRows = numberOfRows;
            return this;
        }

        public TabularDataStructure build() {
            return new TabularDataStructure(rowData, numberOfRows);
        }
    }
}
