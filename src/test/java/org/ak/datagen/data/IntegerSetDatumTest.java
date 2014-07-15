package org.ak.datagen.data;

import static junit.framework.TestCase.fail;
import static org.ak.datagen.data.RandomUtil.WITH_UPPER_BOUND_OF_RANDOM_RANGE;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class IntegerSetDatumTest {

    @Mock
    Random randomMock;

    @Test(expected=IllegalArgumentException.class)
    public void testThatWhenIntegerSetDatumIsConstructedWithEmptySetThenExpectedExceptionIsThrown() {
        IntegerSetDatum integerSetDatum = new IntegerSetDatum("name", new HashSet<Integer>(0));
        fail();
    }

    @Test(expected=IllegalArgumentException.class)
    public void testThatWhenIntegerSetDatumIsConstructedWithemptySetThenExpectedExceptionIsThrown() {
        IntegerSetDatum integerSetDatum = new IntegerSetDatum("Name", null);
        fail();
    }

    @Test
    public void testThatWhenIntegerSetDatumIsConstructedWithSetThatContainsOneIntegerThenGenerateReturnsThatInteger() {
        Integer expectedInteger = 1;
        HashSet<Integer> set = new HashSet<>();
        set.add(expectedInteger);

        IntegerSetDatum integerSetDatum = new IntegerSetDatum("name", set);

        assertThat(integerSetDatum.generate(), is(expectedInteger));
    }

    @Test
    public void testThatWhenRandomNumberGeneratorReturnsLowestNumberInRangeThenTheFirstIntegerInTheSetIsReturnedByGenerate() {
        Integer expectedInteger = 1;
        HashSet<Integer> set = new HashSet<>();
        set.add(expectedInteger);
        set.add(2);
        set.add(3);

        IntegerSetDatum integerSetDatum = new IntegerSetDatum("name", set);
        integerSetDatum.setRandom(randomMock);

        when(randomMock.nextInt(anyInt())).thenReturn(0);

        assertThat(integerSetDatum.generate(), is(expectedInteger));

    }

    @Test
    public void testThatWhenRandomNumberGeneratorReturnsNthNumberInRangeThenNthIntegerInTheSetIsReturnedByGenerate() {

        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        Integer expectedInteger = 3;
        set.add(expectedInteger);
        set.add(4);
        set.add(5);

        IntegerSetDatum integerSetDatum = new IntegerSetDatum("name", set);
        integerSetDatum.setRandom(randomMock);

        when(randomMock.nextInt(anyInt())).thenReturn(2);

        assertThat(integerSetDatum.generate(), is(expectedInteger));
    }

    @Test
    public void testThatWhenRandomNumberGeneratorReturnsHighestNumberInRangeThenTheFirstIntegerInTheSetIsReturnedByGenerate() {

        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        Integer expectedInteger = 5;
        set.add(expectedInteger);

        IntegerSetDatum integerSetDatum = new IntegerSetDatum("name", set);
        integerSetDatum.setRandom(randomMock);

        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_UPPER_BOUND_OF_RANDOM_RANGE);

        assertThat(integerSetDatum.generate(), is(expectedInteger));

    }

}