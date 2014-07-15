package org.ak.datagen.data;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Random;

import static org.ak.datagen.data.RandomUtil.*;

@RunWith(MockitoJUnitRunner.class)
public class StringSetDatumTest {

    @Mock
    Random randomMock;

    @Test(expected=IllegalArgumentException.class)
    public void testThatIfStringSetDatumConstructedWithEmptySetThenExpectedExceptionIsThrown() {
        StringSetDatum stringSetDatum = new StringSetDatum("Name",new HashSet() );
        fail();
    }

    @Test(expected=IllegalArgumentException.class)
    public void testThatIfStringSetDatumConstructedWithNullSetThenExpectedExceptionIsthrown() {
        StringSetDatum stringSetDatum = new StringSetDatum("name", null);
        fail();
    }

    @Test
    public void testThatIfStringSetDatumConstructedWithSetWithOneElementThenGenerateReturnsThatString() {
        HashSet<String> set = new HashSet<>();
        String expectedString = "ONE";
        set.add(expectedString);
        StringSetDatum stringSetDatum = new StringSetDatum("Name", set);

        assertThat(stringSetDatum.generate(), is(expectedString));
    }

    @Test
    public void testThatWhenRandomNumberGeneratorReturnsTheLowestValueInTheRangeThenGenerateReturnsTheFirstElementInTheSet() {

        HashSet<String> set = new HashSet<>();
        String expectedString = "ONE";
        set.add(expectedString);
        set.add("TWO");
        set.add("THREE");

        StringSetDatum stringSetDatum = new StringSetDatum("name", set);
        stringSetDatum.setRandom(randomMock);

        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_LOWER_BOUND_OF_RANDOM_RANGE);

        assertThat(stringSetDatum.generate(), is(expectedString));
    }

    @Test
    public void testThatWhenRandomNumberGeneratorReturnsNThenTheNthElementOfTheStringSetIsReturned() {

        HashSet<String> set = new HashSet<>();
        set.add("ONE");
        String expectedString = "TWO";
        set.add(expectedString);
        set.add("THREE");

        StringSetDatum stringSetDatum = new StringSetDatum("name", set);
        stringSetDatum.setRandom(randomMock);

        when(randomMock.nextInt(anyInt())).thenReturn(1);

        assertThat(stringSetDatum.generate(), is(expectedString));

    }

    @Test
    public void testThatWhenRandomNumberGeneratorReturnsTheHighestValueInTheRangeThenGenerateReturnsTheLastElementInTheSet() {

        HashSet<String> set = new HashSet<>();
        set.add("ONE");
        set.add("TWO");
        String expectedString = "THREE";
        set.add(expectedString);

        StringSetDatum stringSetDatum = new StringSetDatum("name", set);
        stringSetDatum.setRandom(randomMock);

        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_UPPER_BOUND_OF_RANDOM_RANGE);

        assertThat(stringSetDatum.generate(), is(expectedString));
    }

}