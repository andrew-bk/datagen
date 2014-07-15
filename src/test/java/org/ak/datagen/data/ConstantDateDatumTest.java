package org.ak.datagen.data;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

import java.util.Date;

public class ConstantDateDatumTest {

    @Test
    public void testThatWhenConstantDateDatumConstructedWithDateObjectWhenGenerateIsCalledThatDateObjectIsReturned() {
        Date expectedDate = new Date();
        ConstantDateDatum constantDateDatum = new ConstantDateDatum("name", expectedDate);

        assertThat(constantDateDatum.generate(), is(expectedDate));
    }

    @Test
    public void testThatWhenConstantDateDatumConstructedWithStringWhenGeneratIsCalledThatDateIsReturned() {
        // TODO
    }

}