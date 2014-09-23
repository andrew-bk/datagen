package org.ak.datagen.config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class StringCSVElementBodyXmlAdapterTest {

    @Test
    public void testGivenAStringContainingCommaSeparatedListOfUniqueStringsWhenUnMarshallIsCalledThenASetOfTheStringInTheGivenStringIsReturned() throws Exception {

        StringCSVElementBodyXmlAdapter stringCSVElementBodyXmlAdapter = new StringCSVElementBodyXmlAdapter();

        String str = "ONE, TWO, THREE";
        Set<String> set = stringCSVElementBodyXmlAdapter.unmarshal(str);

        assertThat(set, containsInAnyOrder("ONE", "TWO", "THREE"));
    }

    @Test
    public void testGivenAnEmptyStringWhenUnmarshalIsCalledThenAnEmptySetIsReturned() throws Exception {

        StringCSVElementBodyXmlAdapter stringCSVElementBodyXmlAdapter = new StringCSVElementBodyXmlAdapter();

        Set<String> set = stringCSVElementBodyXmlAdapter.unmarshal("");

        assertThat(set, hasSize(0));
    }

    @Test
    public void testGivenAStringContainingANonUniqueListOfStringsWhenUnmarshalIsCalledThenItReturnsASetContainingJustTheUniqueSubset() throws Exception {

        StringCSVElementBodyXmlAdapter stringCsvElementBodyXmlAdapter = new StringCSVElementBodyXmlAdapter();

        HashSet<String> expectedSet = new HashSet<>();
        expectedSet.add("THIS");
        expectedSet.add("THAT");

        Set<String> set = stringCsvElementBodyXmlAdapter.unmarshal("THIS,THIS,THAT");

        assertThat(set, is(expectedSet));
    }

    @Test
    public void testGivenAStringContainingOneStringWhenUnmarshalIsCalledThenItReturnsASetContainingTheSingleString() throws Exception {

        StringCSVElementBodyXmlAdapter stringCSVElementBodyXmlAdapter = new StringCSVElementBodyXmlAdapter();

        String expectedString = "EXPECTED";
        Set<String> set = stringCSVElementBodyXmlAdapter.unmarshal(expectedString);

        assertThat(set, hasSize(1));
        assertThat(set, containsInAnyOrder(expectedString));
    }


}