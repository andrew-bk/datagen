package org.ak.datagen.config;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.ak.datagen.data.Datum;
import org.ak.datagen.data.IntegerSequenceDatum;
import org.junit.Test;

public class SequenceDatumDescriptionTest {

    @Test
    public void testGivenASequenceDatumDescriptionWithNameStartAndStepAttributesSetWhenGetDatumIsCalledThenAnIntegerSequenceDatumIsReturnedCosntructedWithTheGivenNameStartAndStepValues() throws DataMisconfigurationException {

        String expectedName = "sequenceName";
        int expectedStart = 1;
        int expectedStep = 5;
        SequenceDatumDescription sequenceDatumDescription = new SequenceDatumDescription();
        sequenceDatumDescription.setName(expectedName);
        sequenceDatumDescription.setStart(expectedStart);
        sequenceDatumDescription.setStep(expectedStep);

        Datum datum = sequenceDatumDescription.getDatum();

        assertThat(datum, instanceOf(IntegerSequenceDatum.class));
        IntegerSequenceDatum integerSequenceDatum = (IntegerSequenceDatum) datum;
        assertThat(integerSequenceDatum.getName(), is(expectedName));
        assertThat(integerSequenceDatum.getStart(), is(expectedStart));
        assertThat(integerSequenceDatum.getStep(), is(expectedStep));
    }

    @Test
    public void testGivenASequenceDatumDescriptionWithNameAndStartAttributesSetAndStepAttributeIsNullWhenGetDatumIsCalledThenAIntegerSequenceDatumIsGeneratedWithAStepDefaultedToOne() throws DataMisconfigurationException {

        String expectedName = "sequenceName";
        int expectedStart = 1;
        SequenceDatumDescription sequenceDatumDescription = new SequenceDatumDescription();
        sequenceDatumDescription.setName(expectedName);
        sequenceDatumDescription.setStart(expectedStart);
        sequenceDatumDescription.setStep(null);

        Datum datum = sequenceDatumDescription.getDatum();

        assertThat(datum, instanceOf(IntegerSequenceDatum.class));
        IntegerSequenceDatum integerSequenceDatum = (IntegerSequenceDatum) datum;
        assertThat(integerSequenceDatum.getName(), is(expectedName));
        assertThat(integerSequenceDatum.getStart(), is(expectedStart));
        assertThat(integerSequenceDatum.getStep(), is(1));
    }

    @Test
    public void teatGivenASequenceDatumDescriptionWithStartAttributeNotSetWhenGetDatumIsCalledThenAIntegerSequenceDatumIsGeneratedWithStartDefaultedToZero() throws DataMisconfigurationException {

        String expectedName = "sequenceName";
        SequenceDatumDescription sequenceDatumDescription = new SequenceDatumDescription();
        sequenceDatumDescription.setName(expectedName);
        sequenceDatumDescription.setStart(null);
        sequenceDatumDescription.setStep(null);

        Datum datum = sequenceDatumDescription.getDatum();

        assertThat(datum, instanceOf(IntegerSequenceDatum.class));
        IntegerSequenceDatum integerSequenceDatum = (IntegerSequenceDatum) datum;
        assertThat(integerSequenceDatum.getName(), is(expectedName));
        assertThat(integerSequenceDatum.getStart(), is(1));
        assertThat(integerSequenceDatum.getStep(), is(1));
    }
}