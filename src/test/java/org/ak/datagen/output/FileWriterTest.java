package org.ak.datagen.output;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.ak.datagen.data.ConstantIntegerDatum;
import org.ak.datagen.format.CSVFormatter;
import org.ak.datagen.structure.TabularDataStructure;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriterTest {

    public Matcher<StringWriter> numberOfLinesIs(final int numberOfLines) {
        return new BaseMatcher<StringWriter>() {

            private int getNumberOfLines(StringWriter stringWriter) {
                return stringWriter.toString().split("\n").length;
            }

            @Override
            public boolean matches(Object obj) {
                return getNumberOfLines((StringWriter) obj) == numberOfLines;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("number of lines ").appendValue(numberOfLines);
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                description.appendText("was ").appendValue(getNumberOfLines((StringWriter) item));
            }
        };
    }


    @Test
    public void testThatWhenWriteIsCalledPassingACSVFormatterConfiguredWithADataStructureThatIsConfiguredToGenerateAHeaderRowAndNRowsOfDataThenNPlus1LinesAreWrittenToTheFile() throws IOException {
        int HEADER_ROW = 1;
        int N_NUMBER_OF_DATA_ROWS = 5;


        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(new ConstantIntegerDatum("always generates one",1))
                .numberOfRows(N_NUMBER_OF_DATA_ROWS)
                .build();

        CSVFormatter csvFormatter = new CSVFormatter(tabularDataStructure);

        StringWriter writerMock = new StringWriter();

        FileWriter fileWriter = new FileWriter();

        fileWriter.write(writerMock, csvFormatter);

        assertThat(writerMock, numberOfLinesIs(HEADER_ROW + N_NUMBER_OF_DATA_ROWS));
    }
}