package org.ak.datagen.output;

import org.ak.datagen.data.IntegerRangeDatum;
import org.ak.datagen.format.CSVFormatter;
import org.ak.datagen.structure.TabularDataStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleWriterTest {

    @Mock
    PrintStream systemOutMock;

    @InjectMocks
    ConsoleWriter consoleWriter;

    @Test
    public void testThatWhenConsoleWriterIsCreatedThatSystemInIsUsedAsTheDefaultConsole() {
        ConsoleWriter consoleWriter = new ConsoleWriter();

        assertThat(consoleWriter.getConsole(), is(System.out));
    }

    @Test
    public void testThatWhenWriteIsCalledPassingACSVFormatterConfiguredWithADataStructureThatIsConfiguredToGenerateNRowsThenNLinesOfDataAreWrittenToTheConsole() {
        int N_NUMBER_OF_ROWS = 5;

        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(new IntegerRangeDatum("always generates one",1,1))
                .numberOfRows(N_NUMBER_OF_ROWS)
                .build();

        CSVFormatter csvFormatter = new CSVFormatter(tabularDataStructure);

        consoleWriter.write(csvFormatter);

        Mockito.verify(systemOutMock, times(N_NUMBER_OF_ROWS)).println(Mockito.any(String.class));
    }

}