package org.ak.datagen.config;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.ak.datagen.data.Datum;
import org.ak.datagen.data.StringRangeDatum;
import org.junit.Test;

public class RandomStringDatumDescriptionTest {

    @Test
    public void tetsGivenANameAndMinLengthAndMaxLengthWhenGetDatumIsCalledThenStringRangeDatumIsReturnedAndNameMinAndMaxAreSetCorrectly() throws DataMisconfigurationException {

        String expectedName = "randomText";
        int expectedMin = 4;
        int expectedMax = 10;

        RandomStringDatumDescription datumDescription = new RandomStringDatumDescription();
        datumDescription.setName(expectedName);
        datumDescription.setMinLength(expectedMin);
        datumDescription.setMaxLength(expectedMax);

        Datum datum = datumDescription.getDatum();
        assertThat(datum, instanceOf(StringRangeDatum.class));
        StringRangeDatum stringRangeDatum = (StringRangeDatum) datum;

        assertThat(stringRangeDatum.getName(), is(expectedName));
        assertThat(stringRangeDatum.getMinLength(), is(expectedMin));
        assertThat(stringRangeDatum.getMaxLength(), is(expectedMax));
    }

}