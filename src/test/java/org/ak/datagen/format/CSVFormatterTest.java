package org.ak.datagen.format;

import static java.util.stream.Collectors.joining;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.fail;

import org.ak.datagen.data.ConstantStringDatum;
import org.ak.datagen.data.IntegerRangeDatum;
import org.ak.datagen.data.IntegerSequenceDatum;
import org.ak.datagen.structure.TabularDataStructure;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CSVFormatterTest {

    @Test(expected=IllegalArgumentException.class)
    public void testThatWhenCSVFormatterConstructedWithNullDataStructureThenExpectedExceptionIsThrown() {
        CSVFormatter csvFormatter = new CSVFormatter(null);
        fail();
    }

    @Test
    public void testGivenADataStructureWhenGetNextLineIsCalledItReturnsAStringThatIsACommaSeparatedListWithTheSameNumberOfColumnsAsTheDataStructureAndATrailingCommaOnTheEnd() {
        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(
                        new IntegerRangeDatum("one",1,1),
                        new IntegerRangeDatum("two",2,2)
                        )
                .build();

        CSVFormatter csvFormatter = new CSVFormatter(tabularDataStructure);
        String result = csvFormatter.getNextLine();

        String[] generatedColumns = result.split(",");

        assertThat(generatedColumns.length, is(2));
        assertThat(result, endsWith(","));

    }

    @Test
    public void testWhenGetNextLineIsCalledTheGeneratedValueOfEachDatumAppearsInTheReturnedString() {

        int firstDatumValue = 1;
        int secondDatumValue = 2;

        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(
                        new IntegerRangeDatum("always generates one",firstDatumValue,firstDatumValue),
                        new IntegerRangeDatum("always generates two",secondDatumValue,secondDatumValue)
                )
                .build();

        CSVFormatter csvFormatter = new CSVFormatter(tabularDataStructure);
        csvFormatter.printColumnHeadings(false); // To keep test simple
        String result = csvFormatter.getNextLine();

        String[] generatedColumns = result.split(",");

        assertThat(generatedColumns[0], is(equalTo(String.valueOf(firstDatumValue))));
        assertThat(generatedColumns[1], is(equalTo(String.valueOf(secondDatumValue))));
    }

    @Test
    public void testGivenANewlyConstructedCSVFormatterWhenHasNextIsCalledIsReturnsTrue() {

        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(new IntegerRangeDatum("always generates one",1,1))
                .build();

        CSVFormatter csvFormatter = new CSVFormatter(tabularDataStructure);

        assertThat(csvFormatter.hasNext(), is(true));
    }

    @Test
    public void testGivenADataStructureConfiguredToGenerateOneRowAndCSVFormatterSetToNotPrintHeadingsWhenGetNextLineIsCalledAndHasNextIsCalledThenHasNextReturnsFalse() {

        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(new IntegerRangeDatum("always generates one",1,1))
                .numberOfRows(1)
                .build();

        CSVFormatter csvFormatter = new CSVFormatter(tabularDataStructure);
        csvFormatter.printColumnHeadings(false);

        String nextLine = csvFormatter.getNextLine();

        assertThat(csvFormatter.hasNext(), is(false));
    }

    @Test
    public void testThatGivenADataStructureConfiguredToGenerateNRowsAndCSVFormatterConfiguredToPrintHeadersWhenGetNextLineIsCalledNPlus2TimesThenONTheNPlus2ndTimeItShouldReturnAnEmptyStringAndHasNextReturnsFalse() {

        int N_NUMBER_OF_ROWS = 5;
        int HEADER_ROW = 1;

        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(new IntegerRangeDatum("always generates one",1,1))
                .numberOfRows(N_NUMBER_OF_ROWS)
                .build();

        CSVFormatter csvFormatter = new CSVFormatter(tabularDataStructure);

        for(int i = 0; i < N_NUMBER_OF_ROWS + HEADER_ROW; i++) {
            String nextLine = csvFormatter.getNextLine();
        }
        String nPlusTwoLine = csvFormatter.getNextLine();

        assertThat(nPlusTwoLine, isEmptyString());
        assertThat(csvFormatter.hasNext(), is(false));
    }

    @Test
    public void testGivenACSVFormatterWhenGetNextLineCalledThenTheLineReturnedIsACommaSeparatedHeaderRowContainingTheNamesOfTheDataAndEndingWithAComma() {

        List<String> headers = Arrays.asList("ONE", "TEO", "THREE");

        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(new IntegerRangeDatum(headers.get(0),1,5),
                         new IntegerSequenceDatum(headers.get(1), 0, 1),
                         new ConstantStringDatum(headers.get(2), "STRING"))
                .numberOfRows(1)
                .build();

        CSVFormatter csvFormatter = new CSVFormatter(tabularDataStructure);
        String generatedLine = csvFormatter.getNextLine();

        assertThat(generatedLine, is(headers.stream().collect(joining(",", "", ","))));
        assertThat(generatedLine, endsWith(","));

    }

    @Test
    public void testThatGivenACSVFormatterConstructedWithADataStructureConfiguredToProduceZeroRowsThenHasNextReturnsTrue() {

        List<String> headers = Arrays.asList("ONE", "TEO", "THREE");

        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(new IntegerRangeDatum(headers.get(0),1,5),
                        new IntegerSequenceDatum(headers.get(1), 0, 1),
                        new ConstantStringDatum(headers.get(2), "STRING"))
                .numberOfRows(0)
                .build();

        CSVFormatter csvFormatter = new CSVFormatter(tabularDataStructure);

        assertThat(csvFormatter.hasNext(), is(true));
    }

    @Test
    public void testThatGivenACSVFormatterConstructedWithADataStructureConfiguredToProduceZeroRowsAndPrintHeadersSetToFalseWhenHasNextReturnsFalse() {

        List<String> headers = Arrays.asList("ONE", "TEO", "THREE");

        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(new IntegerRangeDatum(headers.get(0),1,5),
                        new IntegerSequenceDatum(headers.get(1), 0, 1),
                        new ConstantStringDatum(headers.get(2), "STRING"))
                .numberOfRows(0)
                .build();

        CSVFormatter csvFormatter = new CSVFormatter(tabularDataStructure);
        csvFormatter.printColumnHeadings(false);

        assertThat(csvFormatter.hasNext(), is(false));

    }
}