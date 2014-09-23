package org.ak.datagen.config;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class IntegerCSVElementBodyXmlAdapterTest {

    @Test
    public void testGivenAStringContainingCommaSeparatedListOfUniqueIntegersWhenUnMarshallIsCalledThenASetOfTheIntegersInTheGivenStringISReturned() throws Exception {

        IntegerCSVElementBodyXmlAdapter integerCsvElementBodyXmlAdapter = new IntegerCSVElementBodyXmlAdapter();

        String str = "1,2,3";
        Set<Integer> set = integerCsvElementBodyXmlAdapter.unmarshal(str);

        assertThat(set, containsInAnyOrder(1,2,3));
    }

    @Test
    public void testGivenAnEmptyStringWhenUnmarshalIsCalledThenAnEmptySetIsReturned() throws Exception {

        IntegerCSVElementBodyXmlAdapter integerCsvElementBodyXmlAdapter = new IntegerCSVElementBodyXmlAdapter();

        Set<Integer> set = integerCsvElementBodyXmlAdapter.unmarshal("");

        assertThat(set, hasSize(0));
    }

    @Test(expected=NumberFormatException.class)
    public void testGivenAStringContainingNonNumericTextWhenUnmarshalIsCalledThenNumberFormatExceptionIsThrown() throws Exception {

        IntegerCSVElementBodyXmlAdapter integerCsvElementBodyXmlAdapter = new IntegerCSVElementBodyXmlAdapter();

        Set<Integer> set = integerCsvElementBodyXmlAdapter.unmarshal("notANumber");
        fail();
    }

    @Test
    public void testGivenAStringContainingSomeANonUniqueListOfIntegersWhenUnmarshalIsCalledThenItReturnsASetContainingJustTheUniqueSubset() throws Exception {

        IntegerCSVElementBodyXmlAdapter integerCsvElementBodyXmlAdapter = new IntegerCSVElementBodyXmlAdapter();

        HashSet<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);

        Set<Integer> set = integerCsvElementBodyXmlAdapter.unmarshal("1,1,2");

        assertThat(set, is(expectedSet));
    }

    @Test
    public void testGivenAStringContainingOneNumberWhenUnmarshalIsCalledThenItReturnsASetContainingTheSingleNumber() throws Exception {

        IntegerCSVElementBodyXmlAdapter integerCsvElementBodyXmlAdapter = new IntegerCSVElementBodyXmlAdapter();

        Set<Integer> set = integerCsvElementBodyXmlAdapter.unmarshal("42");

        assertThat(set, hasSize(1));
        assertThat(set, containsInAnyOrder(42));
    }

    @Test(expected=NumberFormatException.class)
    public void testGivenAStringContainingANonIntegerNumericValueWhenUnmarshalIsCalledThenNumberFormatExceptionIsThrown() throws Exception {
        IntegerCSVElementBodyXmlAdapter integerCsvElementBodyXmlAdapter = new IntegerCSVElementBodyXmlAdapter();

        Set<Integer> set = integerCsvElementBodyXmlAdapter.unmarshal("42.4, 3");
        fail();
    }

}