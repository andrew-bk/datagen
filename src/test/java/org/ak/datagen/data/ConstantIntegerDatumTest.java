package org.ak.datagen.data;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class ConstantIntegerDatumTest {

    @Test
    public void testThatWhenConstantIntegerDatumGenerateMethodIsCalledThenTheGivenValueInTheConstructorIsReturned() {
        int expectedValue = 12;
        ConstantIntegerDatum constantIntegerDatum = new ConstantIntegerDatum("name", expectedValue);

        assertThat(constantIntegerDatum.generate(), is(instanceOf(Integer.class)));
        assertThat(constantIntegerDatum.generate(), is(expectedValue));
    }

}