package org.ak.datagen.data;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.ak.datagen.data.RandomUtil.*;

import java.util.Random;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IntegerRangeDatumTest {

    @Mock
    Random randomMock;



    @Test(expected=IllegalArgumentException.class)
    public void testThatWhenAnIntegerRangeDatumIsConstructedWithAMaxValueGreaterThanAMinValue() {
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", 10, 5);
        fail();
    }

    @Test
    public void testThatWhenIntegerRangeDatumIsConstructedThatNameIsSetCorrectly() {
        String expectedName = "name";
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum(expectedName, 1, 3);
        assertThat(integerRangeDatum.getName(), is(equalTo(expectedName)));
    }

    @Test
    public void testThatIfIntegerRangeDatumConstructedWithMinAndMaxTheSameThenTheNumberItGeneratesIsTheSameASTheMinAndMax() {
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", 5,5);

        assertThat(integerRangeDatum.generate(), is(equalTo(5)));
    }

    @Test
    public void testThatWhenTheRandomNumberGeneratorReturnsTheUpperBoundThenIntegerRangeDatumReturnsTheValueOfMax() {

        int max = 10;
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", 0, max);
        integerRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_UPPER_BOUND_OF_RANDOM_RANGE);

        assertThat(integerRangeDatum.generate(), is(equalTo(max)));
    }

    @Test
    public void testThatWhenTheRandomNumberGeneratorReturnsTheLowerBoundThenIntegerRangeDatumReturnsTheValueOfMin() {

        int min = 0;
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", min, 10);
        integerRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_LOWER_BOUND_OF_RANDOM_RANGE);

        assertThat(integerRangeDatum.generate(), is(equalTo(min)));

    }

    @Test
    public void testThatGivenAnIntegerRangeDatumWithANegativeMinValueAndPositiveMaxValueWhenTheRandomNumberGeneratorReturnsTheLowerBoundThenIntegerRangeDatumReturnsTheValueOfMin() {
        int min = -20;
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", min, 10);
        integerRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_LOWER_BOUND_OF_RANDOM_RANGE);

        assertThat(integerRangeDatum.generate(), is(equalTo(min)));
    }

    @Test
    public void testThatGivenAnIntegerRangeDatumWithANegativeMinValueAndPositiveMaxValueWhenTheRandomNumberGeneratorReturnsTheUpperBoundThenIntegerRangeDatumReturnsTheValueOfMax() {
        int max = 10;
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", -20, max);
        integerRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_UPPER_BOUND_OF_RANDOM_RANGE);

        assertThat(integerRangeDatum.generate(), is(equalTo(max)));
    }

    @Test
    public void testThatGivenAnIntegerRangeDatumWithANegativeMinValueAndNegativeMaxValueWhenTheRandomNumberGeneratorReturnsTheLowerBoundThenIntegerRangeDatumReturnsTheValueOfMin() {
        int min = -40;
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", min, -30);
        integerRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_LOWER_BOUND_OF_RANDOM_RANGE);

        assertThat(integerRangeDatum.generate(), is(equalTo(min)));
    }


    @Test
    public void testThatGivenAnIntegerRangeDatumWithANegativeMinValueAndNegativeMaxValueWhenTheRandomNumberGeneratorReturnsTheUpperBoundThenIntegerRangeDatumReturnsTheValueOfMax() {
        int max = -12;
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", -75, max);
        integerRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_UPPER_BOUND_OF_RANDOM_RANGE);

        assertThat(integerRangeDatum.generate(), is(equalTo(max)));
    }

}