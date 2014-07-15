package org.ak.datagen.data;

import static org.ak.datagen.data.RandomUtil.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class DecimalRangeDatumTest {

    @Mock
    Random randomMock;

    @Test
    public void testWhenDecimalRangeDatumGeneratesAValueThenTheValueHasTheSameNumberOfDecimalPlacesAsGivenInTheConstructor() {
        int expectedNumberOfDecimalPlaces = 3;
        DecimalRangeDatum decimalRangeDatum = new DecimalRangeDatum("name", 1, 10, expectedNumberOfDecimalPlaces);
        decimalRangeDatum.setRandom(randomMock);

        assertThat(decimalRangeDatum.generate().scale(), is(expectedNumberOfDecimalPlaces));
    }

    @Test
    public void testWhenRandomNumberGeneratorReturnsLowestNumberInRangeThenTheValueOfMinIsReturned() {
        int min = 0;
        int numDp = 2;
        DecimalRangeDatum decimalRangeDatum = new DecimalRangeDatum("name", min, 10, numDp);
        decimalRangeDatum.setRandom(randomMock);

        when(randomMock.nextDouble()).thenReturn(0.0);

        BigDecimal expectedDecimal = new BigDecimal(min).setScale(numDp, RoundingMode.UNNECESSARY);

        assertThat(decimalRangeDatum.generate(), is(expectedDecimal));
    }

    @Test
    public void testwhenRandomNumberGeneratorReturnsHighestNumberInRangeThenTheValueOfMaxIsReturned() {
        int max = 1000;
        int numDp = 2;
        DecimalRangeDatum decimalRangeDatum = new DecimalRangeDatum("name", 0, max, numDp);
        decimalRangeDatum.setRandom(randomMock);

        when(randomMock.nextDouble()).thenReturn(1.0);

        BigDecimal expectedDecimal = new BigDecimal(max).setScale(numDp, RoundingMode.UNNECESSARY);

        assertThat(decimalRangeDatum.generate(), is(expectedDecimal));
    }

    @Test
    public void testWhenMinIsNegativeAndMaxIsPositiveAndRandomNumberGeneratorReturnsLowestNumberInRangeThenMinIsReturned() {
        int min = -100;
        int numDp = 2;
        DecimalRangeDatum decimalRangeDatum = new DecimalRangeDatum("name", min, 10, numDp);
        decimalRangeDatum.setRandom(randomMock);

        when(randomMock.nextDouble()).thenReturn(0.0);

        BigDecimal expectedDecimal = new BigDecimal(min).setScale(numDp, RoundingMode.UNNECESSARY);

        assertThat(decimalRangeDatum.generate(), is(expectedDecimal));
    }

    @Test
    public void testWhenMinIsNegativeAndMaxIsNegativeAndRandomNumberGeneratorReturnsHighestNumberInRangeThenMaxIsReturned() {
        int min = -100;
        int max = -10;
        int numDp = 2;
        DecimalRangeDatum decimalRangeDatum = new DecimalRangeDatum("name", min, max, numDp);
        decimalRangeDatum.setRandom(randomMock);

        when(randomMock.nextDouble()).thenReturn(1.0);

        BigDecimal expectedDecimal = new BigDecimal(max).setScale(numDp, RoundingMode.UNNECESSARY);

        assertThat(decimalRangeDatum.generate(), is(expectedDecimal));
    }
}