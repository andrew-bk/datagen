package org.ak.datagen.structure;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.fail;

import org.ak.datagen.data.IntegerRangeDatum;
import org.junit.Test;

public class TabularDataStructureTest {

    @Test(expected=IllegalArgumentException.class)
    public void testThatIfTabularDataBuilderIsUsedToBuildATabularDataStructureWithNoRowDataThenExpectedExceptionIsThrown() {
        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder().build();
        fail();
    }

    @Test
    public void testThatIfTabularDataBuilderIsUsedToBuildATabularDataStructureWithSomeRowDataThenTheBuiltTabularDataStructureHasTheRowData() {
        IntegerRangeDatum expectedDatum = new IntegerRangeDatum("name", 0,10);

        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(expectedDatum)
                .build();

        assertThat(tabularDataStructure.getRowData(), containsInAnyOrder(expectedDatum));
    }

    @Test
    public void testThatWhenTabularDataBuilderIsusedToBuildATabularDataStructureAndtheNumberOfRowsIsNotSpecifiedThenNumberOfRowsIsSetToOne() {
        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(new IntegerRangeDatum("name", 1,10))
                .build();
        assertThat(tabularDataStructure.getNumberOfRows(), is(1));
    }

    @Test
    public void testThatWhenTabularDataBuilderIsUsedToBuildATabularDataStructureAndTheNumberOfRowsIsSpecifiedThenTheNumberOfRowsIsSetToTheGivenValue() {
        int expectedNumberOfRows = 12;

        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(new IntegerRangeDatum("name", 1,10))
                .numberOfRows(expectedNumberOfRows)
                .build();

        assertThat(tabularDataStructure.getNumberOfRows(), is(expectedNumberOfRows));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testThatWhenTabularDataBuilderIsUsedToBuildATabularDataStructureAndNoDatumsAreSpecifiedThenExpectedExceptionIsThrown() {
        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .build();
        fail();
    }

}