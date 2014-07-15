package org.ak.datagen.data;

import static org.ak.datagen.data.RandomUtil.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(MockitoJUnitRunner.class)
public class StringRangeDatumTest {

    @Mock
    Random randomMock;

    @Test
    public void testThatWhenRandomNumberGeneratorReturnsTheMaximumBoundThenStringRangeDatumGeneratesAStringOfMaxLength() {
        int max = 10;
        StringRangeDatum stringRangeDatum = new StringRangeDatum("name", 1, max);
        stringRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_UPPER_BOUND_OF_RANDOM_RANGE);

        assertThat(((String) stringRangeDatum.generate()).length(), is(max));
    }

    @Test
    public void testThatGivenAStringRangeDatumWithAMinimumGreaterThanZeroWhenRandomNumberGeneratorReturnsTheLowerBoundThenStringRangeDatumGeneratesAStringOfMinLength() {
        int min = 1;
        StringRangeDatum stringRangeDatum = new StringRangeDatum("name", min, 23);
        stringRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_LOWER_BOUND_OF_RANDOM_RANGE);

        assertThat(((String) stringRangeDatum.generate()).length(), is(min));
    }

    @Test
    public void testThatGivenAStringRangeDatumWithAMinimumOfZeroWhenRandomNumberGeneratorReturnsTheLowerBoundThenStringRangeDatumGeneratesAStringOfMinLength() {
        int min = 0;
        StringRangeDatum stringRangeDatum = new StringRangeDatum("name", min, 23);
        stringRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_LOWER_BOUND_OF_RANDOM_RANGE);

        assertThat(((String) stringRangeDatum.generate()).length(), is(min));
    }

    @Test
    public void testThatStringRangeDatumGeneratesStringsThatAreComposedOfAlphanumericMixedCaseCharacters() {
        int min = 0;
        StringRangeDatum stringRangeDatum = new StringRangeDatum("name", min, 23);
        stringRangeDatum.setRandom(randomMock);
        when(randomMock.nextInt(anyInt())).thenAnswer(WITH_LOWER_BOUND_OF_RANDOM_RANGE);

        Pattern pattern = Pattern.compile("[0-9a-zA-Z]*");
        Matcher m = pattern.matcher(stringRangeDatum.generate().toString());

        assertTrue(m.matches());
    }

}