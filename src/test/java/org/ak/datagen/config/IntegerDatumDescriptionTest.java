package org.ak.datagen.config;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import org.ak.datagen.data.ConstantIntegerDatum;
import org.ak.datagen.data.Datum;
import org.ak.datagen.data.IntegerRangeDatum;
import org.ak.datagen.data.IntegerSetDatum;
import org.junit.Test;

import java.util.HashSet;

public class IntegerDatumDescriptionTest {

    @Test
    public void testGivenConstantAttributeIsNotNullAndFromToAndSetAttributesAreAllNullThenGetDatumReturnsAConstantIntegerDatumConstructedWithTheValueOfTheConstantAttribute() throws DataMisconfigurationException {

        int expectedConstantValue = 42;
        IntegerDatumDescription integerDatumDescription = new IntegerDatumDescription();
        integerDatumDescription.setName("name");
        integerDatumDescription.setConstant(expectedConstantValue);
        integerDatumDescription.setFrom(null);
        integerDatumDescription.setTo(null);
        integerDatumDescription.setSet(null);

        Datum datum = integerDatumDescription.getDatum();

        assertThat(datum, instanceOf(ConstantIntegerDatum.class));
        assertThat(((ConstantIntegerDatum) datum).getConstantValue(), is(expectedConstantValue));
    }

    @Test(expected=DataMisconfigurationException.class)
    public void testGivenAnIntegerDatumDescriptionWhereAllAttributesExceptNameAreNullWhenGetDatumIsCalledThenExpectedExceptionIsThrown() throws DataMisconfigurationException {

        IntegerDatumDescription integerDatumDescription = new IntegerDatumDescription();
        integerDatumDescription.setName("name");
        integerDatumDescription.setConstant(null);
        integerDatumDescription.setFrom(null);
        integerDatumDescription.setTo(null);
        integerDatumDescription.setSet(null);

        Datum datum = integerDatumDescription.getDatum();
        fail();
    }

    @Test(expected=DataMisconfigurationException.class)
    public void testGivenAnIntegerDatumDescriptionWhereConstantAttributeIsNotNullAndFromAttributeIsNotNullWhenGetDatumIsCalledThenExpectedExceptionIsthrown() throws DataMisconfigurationException {

        IntegerDatumDescription integerDatumDescription = new IntegerDatumDescription();
        integerDatumDescription.setName("name");
        integerDatumDescription.setConstant(42);
        integerDatumDescription.setFrom(42);
        integerDatumDescription.setTo(null);
        integerDatumDescription.setSet(null);

        Datum datum = integerDatumDescription.getDatum();
        fail();
    }

    @Test(expected=DataMisconfigurationException.class)
    public void testGivenAnIntegerDatumDescriptionWhereConstantAttributeIsNotNullAndToAttributeIsNotNullWhenGetDatumIsCalledThenExpectedExceptionIsThrown() throws DataMisconfigurationException {

        IntegerDatumDescription integerDatumDescription = new IntegerDatumDescription();
        integerDatumDescription.setName("name");
        integerDatumDescription.setConstant(42);
        integerDatumDescription.setFrom(null);
        integerDatumDescription.setTo(34);
        integerDatumDescription.setSet(null);

        Datum datum = integerDatumDescription.getDatum();
        fail();
    }


    @Test(expected=DataMisconfigurationException.class)
    public void testGivenAnIntegerDatumDescriptionWhereConstantAttributeIsNotNullAndSetAttributeIsNotNullWhenGetDatumIsCalledThenExpectedExceptionIsThrown() throws DataMisconfigurationException {

        IntegerDatumDescription integerDatumDescription = new IntegerDatumDescription();
        integerDatumDescription.setName("name");
        integerDatumDescription.setConstant(42);
        integerDatumDescription.setFrom(null);
        integerDatumDescription.setTo(null);
        integerDatumDescription.setSet(new HashSet<Integer>());

        Datum datum = integerDatumDescription.getDatum();
        fail();
    }

    @Test
    public void testGivenAnIntegerDatumDescriptionWhereFromAndToAreNotNullAndAllOtherAttributesExceptNameAreNullWhenGetDatumIsCalledThenIntegerRangeDatumIsReturnedConstructedWithTheGivenFromAndToValues() throws DataMisconfigurationException {

        int expectedFromValue = 42;
        int expectedToValue = 55;
        IntegerDatumDescription integerDatumDescription = new IntegerDatumDescription();
        integerDatumDescription.setName("name");
        integerDatumDescription.setConstant(null);
        integerDatumDescription.setFrom(expectedFromValue);
        integerDatumDescription.setTo(expectedToValue);
        integerDatumDescription.setSet(null);

        Datum datum = integerDatumDescription.getDatum();

        assertThat(datum, instanceOf(IntegerRangeDatum.class));
        IntegerRangeDatum integerRangeDatum = (IntegerRangeDatum) datum;
        assertThat(integerRangeDatum.getFrom(), is(expectedFromValue));
        assertThat(integerRangeDatum.getTo(), is(expectedToValue));
    }

    @Test(expected=DataMisconfigurationException.class)
    public void testGivenAnIntegerDatumDescriptionWithFromAndToAttributesAreNotNullAndConstantAttributeIsNotNullWhenGetDatumIsCalledThenExpectedExceptionIsThrown() throws DataMisconfigurationException {

        int expectedFromValue = 42;
        int expectedToValue = 55;
        IntegerDatumDescription integerDatumDescription = new IntegerDatumDescription();
        integerDatumDescription.setName("name");
        integerDatumDescription.setConstant(77);
        integerDatumDescription.setFrom(expectedFromValue);
        integerDatumDescription.setTo(expectedToValue);
        integerDatumDescription.setSet(null);

        Datum datum = integerDatumDescription.getDatum();
        fail();
    }

    @Test(expected=DataMisconfigurationException.class)
    public void testGivenAnIntegerDatumDescriptionWithFromAndToAttributesAreNotNullAndSetAttributeIsNotNullWhenGetDatumIsCalledThenExpectedExceptinoIsThrown() throws DataMisconfigurationException {

        IntegerDatumDescription integerDatumDescription = new IntegerDatumDescription();
        integerDatumDescription.setName("name");
        integerDatumDescription.setConstant(null);
        integerDatumDescription.setFrom(1);
        integerDatumDescription.setTo(20);
        integerDatumDescription.setSet(new HashSet<Integer>());

        Datum datum = integerDatumDescription.getDatum();
        fail();
    }

    @Test
    public void testGivenAnIntegerDatumDescriptionWithSetAttributeIsNotNullAndAllOtherAttributesExceptNameAreNullWhenGetDatumIsCalledThenAnIntegerSetDatumIsReturnedAndIsConstructedWithTheGivenSetOfIntegers() throws DataMisconfigurationException {

        HashSet<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);
        expectedSet.add(3);
        IntegerDatumDescription integerDatumDescription = new IntegerDatumDescription();
        integerDatumDescription.setName("name");
        integerDatumDescription.setConstant(null);
        integerDatumDescription.setFrom(null);
        integerDatumDescription.setTo(null);
        integerDatumDescription.setSet(expectedSet);

        Datum datum = integerDatumDescription.getDatum();

        assertThat(datum, instanceOf(IntegerSetDatum.class));
        assertThat(((IntegerSetDatum) datum).getSet(), is(expectedSet));
    }

    @Test(expected=DataMisconfigurationException.class)
    public void testGivenAnIntegerDatumDescriptionWithSetNotNullAndConstantIsNotNullAndAllOtherAttributesExceptNameAreNullWhenGetDatumIsCalledThenExpectedExceptionIsThrown() throws DataMisconfigurationException {

        HashSet<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);
        expectedSet.add(3);
        IntegerDatumDescription integerDatumDescription = new IntegerDatumDescription();
        integerDatumDescription.setName("name");
        integerDatumDescription.setConstant(42);
        integerDatumDescription.setFrom(null);
        integerDatumDescription.setTo(null);
        integerDatumDescription.setSet(expectedSet);

        Datum datum = integerDatumDescription.getDatum();
        fail();
    }

    @Test(expected=DataMisconfigurationException.class)
    public void testGivenAnIntegerRangeDatumWithSetNotNullAndFromIsNotNullAndAllOtherAttributesExceptNameAreNullWhenGetDatumIsCalledThenExpectedExceptionIsThrown() throws DataMisconfigurationException {

        HashSet<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);
        expectedSet.add(3);
        IntegerDatumDescription integerDatumDescription = new IntegerDatumDescription();
        integerDatumDescription.setName("name");
        integerDatumDescription.setConstant(null);
        integerDatumDescription.setFrom(42);
        integerDatumDescription.setTo(null);
        integerDatumDescription.setSet(expectedSet);

        Datum datum = integerDatumDescription.getDatum();
        fail();
    }

    @Test(expected=DataMisconfigurationException.class)
    public void testGivenAnIntegerRangeDatumWithSetNotNullAndToIsNotNullAndAllOtherAttributesExceptNameAreNullWhenGetDatumIsCalledThenExpectedExceptionIsThrown() throws DataMisconfigurationException {

        HashSet<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);
        expectedSet.add(3);
        IntegerDatumDescription integerDatumDescription = new IntegerDatumDescription();
        integerDatumDescription.setName("name");
        integerDatumDescription.setConstant(null);
        integerDatumDescription.setFrom(null);
        integerDatumDescription.setTo(42);
        integerDatumDescription.setSet(expectedSet);

        Datum datum = integerDatumDescription.getDatum();
        fail();
    }
}