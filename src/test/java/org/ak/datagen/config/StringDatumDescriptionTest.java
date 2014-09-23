package org.ak.datagen.config;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.fail;

import org.ak.datagen.data.ConstantStringDatum;
import org.ak.datagen.data.Datum;
import org.ak.datagen.data.StringSetDatum;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class StringDatumDescriptionTest {

    @Test
    public void testGivenAStringDatumDescriptionWithNameAttributeSetAndConstantAttributeSetAndSetAttributeIsNotSetWhenGetDatumIsCalledThenItReturnsAConstantStringDatumWithTheGivenNameAndConstant() throws DataMisconfigurationException {

        String expectedName = "name";
        String expectedConstant = "CONSTANT";
        StringDatumDescription stringDatumDescription = new StringDatumDescription();
        stringDatumDescription.setName(expectedName);
        stringDatumDescription.setConstant(expectedConstant);

        Datum datum = stringDatumDescription.getDatum();

        assertThat(datum, instanceOf(ConstantStringDatum.class));
        ConstantStringDatum constantStringDatum = (ConstantStringDatum) datum;

        assertThat(constantStringDatum.getName(), is(expectedName));
        assertThat(constantStringDatum.getConstantValue(), is(expectedConstant));
    }

    @Test(expected=DataMisconfigurationException.class)
    public void testGivenAStringDatumDescriptionWithNameConstantAndSetAttributesAllSetWhenGetDatumIsCalledThenExpectedExceptionIsThrown() throws DataMisconfigurationException {

        StringDatumDescription stringDatumDescription = new StringDatumDescription();
        stringDatumDescription.setName("name");
        stringDatumDescription.setConstant("constant");
        stringDatumDescription.setSet(new HashSet<>());

        Datum datum = stringDatumDescription.getDatum();
        fail();
    }

    @Test
    public void testGivenAStringDatumDescriptionWithNameAndSetAttributesSetAndConstantAttributeIsNotSetWhenGetDatumIsCalledThenAStringSetDatumIsReturnedWithTheGivenNameAndSet() throws DataMisconfigurationException {

        String expectedName = "name";
        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("ONE");
        expectedSet.add("TWO");
        expectedSet.add("THREE");
        StringDatumDescription stringDatumDescription = new StringDatumDescription();
        stringDatumDescription.setName(expectedName);
        stringDatumDescription.setSet(expectedSet);

        Datum datum = stringDatumDescription.getDatum();

        assertThat(datum, instanceOf(StringSetDatum.class));
        StringSetDatum stringSetDatum = (StringSetDatum) datum;
        assertThat(stringSetDatum.getName(), is(expectedName));
        assertThat(stringSetDatum.getSet(), is(expectedSet));

    }

    @Test(expected = DataMisconfigurationException.class)
    public void testGivenAStringDatumDescriptionWithOnlyNameAttributeSetWhenGetDatumIsCalledThenExpectedExceptionIsThrown() throws DataMisconfigurationException {
        StringDatumDescription stringDatumDescription = new StringDatumDescription();
        stringDatumDescription.setName("name");

        stringDatumDescription.getDatum();
        fail();
    }
}