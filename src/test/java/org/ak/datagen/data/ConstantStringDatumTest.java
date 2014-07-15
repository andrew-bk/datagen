package org.ak.datagen.data;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.fail;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.junit.Test;

public class ConstantStringDatumTest {

    @Test(expected=IllegalArgumentException.class)
    public void testThatIfConstantStringDatumConstructedWithNullConstantValueThenExpectedExceptionIsThrown() {
        ConstantStringDatum constantStringDatum = new ConstantStringDatum("name", null);
        fail();
    }

    @Test
    public void testThatWhenGenerateMethodIsCalledOnConstantStringDatumThenTheConstantValueFromTheConstructorIsReturned() {
        String expectedValue = "CONSTANT";
        ConstantStringDatum constantStringDatum = new ConstantStringDatum("name", expectedValue);

        assertThat(constantStringDatum.generate(), is(instanceOf(String.class)));
        assertThat(constantStringDatum.generate(), is(expectedValue));
    }

}