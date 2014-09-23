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
    public void testThatWhenAnIntegerRangeDatumIsConstructedWithAToValueGreaterThanAFromValue() {
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
    public void testThatIfIntegerRangeDatumConstructedWithFromAndToTheSameThenTheNumberItGeneratesIsTheSameASTheFromAndTo() {
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", 5,5);

        assertThat(integerRangeDatum.generate(), is(equalTo(5)));
    }

    @Test
    public void testThatWhenTheRandomNumberGeneratorReturnsTheUpperBoundThenIntegerRangeDatumReturnsTheValueOfTo() {

        int to = 10;
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", 0, to);
        integerRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_UPPER_BOUND_OF_RANDOM_RANGE);

        assertThat(integerRangeDatum.generate(), is(equalTo(to)));
    }

    @Test
    public void testThatWhenTheRandomNumberGeneratorReturnsTheLowerBoundThenIntegerRangeDatumReturnsTheValueOfFrom() {

        int from = 0;
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", from, 10);
        integerRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_LOWER_BOUND_OF_RANDOM_RANGE);

        assertThat(integerRangeDatum.generate(), is(equalTo(from)));

    }

    @Test
    public void testThatGivenAnIntegerRangeDatumWithANegativeFromValueAndPositiveToValueWhenTheRandomNumberGeneratorReturnsTheLowerBoundThenIntegerRangeDatumReturnsTheValueOfFrom() {
        int from = -20;
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", from, 10);
        integerRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_LOWER_BOUND_OF_RANDOM_RANGE);

        assertThat(integerRangeDatum.generate(), is(equalTo(from)));
    }

    @Test
    public void testThatGivenAnIntegerRangeDatumWithANegativeFromValueAndPositiveToValueWhenTheRandomNumberGeneratorReturnsTheUpperBoundThenIntegerRangeDatumReturnsTheValueOfTo() {
        int to = 10;
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", -20, to);
        integerRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_UPPER_BOUND_OF_RANDOM_RANGE);

        assertThat(integerRangeDatum.generate(), is(equalTo(to)));
    }

    @Test
    public void testThatGivenAnIntegerRangeDatumWithANegativeFromValueAndNegativeToValueWhenTheRandomNumberGeneratorReturnsTheLowerBoundThenIntegerRangeDatumReturnsTheValueOfFrom() {
        int from = -40;
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", from, -30);
        integerRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_LOWER_BOUND_OF_RANDOM_RANGE);

        assertThat(integerRangeDatum.generate(), is(equalTo(from)));
    }


    @Test
    public void testThatGivenAnIntegerRangeDatumWithANegativeFromValueAndNegativeToValueWhenTheRandomNumberGeneratorReturnsTheUpperBoundThenIntegerRangeDatumReturnsTheValueOfTo() {
        int to = -12;
        IntegerRangeDatum integerRangeDatum = new IntegerRangeDatum("name", -75, to);
        integerRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_UPPER_BOUND_OF_RANDOM_RANGE);

        assertThat(integerRangeDatum.generate(), is(equalTo(to)));
    }

}