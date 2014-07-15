package org.ak.datagen.data;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.fail;

import org.junit.Test;

public class DatumTest {

    @Test(expected=IllegalArgumentException.class)
    public void testThatIfDatumCreatedWithNullNameThenExpectedExceptionIsThrown() {
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum(null, 1, 10);
        fail();
    }

    @Test
    public void testThatWhenGetNameIsCalledItReturnsTheNameGivenInTheConstructor() {
        String expectedName = "this_is_the_name";
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum(expectedName, 1, 10);

        assertThat(integerRangeDatum.getName(), is(expectedName));
    }

}