package org.ak.datagen.data;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.fail;
import org.junit.Test;

public class IntegerSequenceDatumTest {

    @Test
    public void testThatIntegerSequenceGeneratesIntegers() {
        IntegerSequenceDatum integerSequenceDatum = new IntegerSequenceDatum("name", 1, 1);

        assertThat(integerSequenceDatum.generate(), is(instanceOf(Integer.class)));
    }

    @Test
    public void testThatGivenANewlyConstructedIntegerSequenceDatumTheFirstTimeThatGenerateIsCalledItReturnsTheValueOfStart() {
        int start = 0;

        IntegerSequenceDatum integerSequenceDatum = new IntegerSequenceDatum("name", start, 1);
        assertThat(integerSequenceDatum.generate(), is(start));
    }

    @Test
    public void testThatGivenANewlyConstructedIntegerSequenceDatumWithAStepThatIsGreaterThanZeroWhenGenerateIsCalledASecondTimeItReturnsTheValueOfStartPlusTheStep() {
        int start = 1;
        int step = 5;
        IntegerSequenceDatum integerSequenceDatum = new IntegerSequenceDatum("name", start, step);
        integerSequenceDatum.generate();
        assertThat(integerSequenceDatum.generate(), is(start + step));
    }

    @Test
    public void testThatGivenANewlyConstructedIntegerSequenceDatumWithAStepThatIsLessThanZeroWhenGenerateIsCalledASecondTimeItReturnsTheValueOfStartMinusStep() {
        int start = 1;
        int step = -3;
        IntegerSequenceDatum integerSequenceDatum = new IntegerSequenceDatum("name", start, step);
        integerSequenceDatum.generate();
        assertThat(integerSequenceDatum.generate(), is(start + step));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testThatWhenIntegerSequenceIsCreatedWithAStepOfZeroThenExpectedExceptionIsThrown() {
        IntegerSequenceDatum integerSequenceDatum = new IntegerSequenceDatum("name", 1,0);
        fail();
    }

}