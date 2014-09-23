package org.ak.datagen.config;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.fail;

import org.ak.datagen.data.ConstantDateDatum;
import org.ak.datagen.data.Datum;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Date;

public class DateDatumDescriptionTest {



    @Test
    public void testGivenConstantAttributeIsNotNullAndOnlyContainsDateInformationAndDisplayFormatIsNullAndFromAndToAreNullWhenGetDatumIsCalledThenConstantDateDatumIsReturnedAndItGeneratesTheGivenDateAndFormatsItInTheDefaultPatternAndTheNameIsSet() {

        String expectedName = "constant_date";
        String constantDateValue = "24/06/1982";
        ZonedDateTime expectedGeneratedDate = ZonedDateTime.of(1982, 6, 24, 0, 0, 0, 0, ZoneId.systemDefault());

        try {
            DateDatumDescription dateDatumDescription = new DateDatumDescription();
            dateDatumDescription.setName(expectedName);
            dateDatumDescription.setConstant(constantDateValue);

            Datum datum = dateDatumDescription.getDatum();

            assertThat(datum, instanceOf(ConstantDateDatum.class));
            ConstantDateDatum constantDateDatum = (ConstantDateDatum) datum;
            assertThat(constantDateDatum.getName(), is(equalTo(expectedName)));
            assertThat(constantDateDatum.generate(), is(equalTo(expectedGeneratedDate)));
            assertThat(constantDateDatum.format(), is(equalTo(constantDateValue)));

        } catch (DataMisconfigurationException e) {
            e.printStackTrace();
            fail("Unexpected Data Misconfiguration error");
        }
    }

    @Test(expected=DataMisconfigurationException.class)
    public void testGivenConstantIsNotNullAndDoesNotMatchDefaultDateFormatAndFromAndToAreNullWhenGetDatumIsCalledThenExpectedExcceptinoIsThrown() throws DataMisconfigurationException {

        DateDatumDescription dateDatumDescription = new DateDatumDescription();
        dateDatumDescription.setName("name");
        dateDatumDescription.setConstant("ghfkjfghf");

        dateDatumDescription.getDatum();
        fail("Expected exception was not thrown");
    }

    @Test(expected=DataMisconfigurationException.class)
    public void testGivenConstantIsNotNullAndFromIsNotNullWhenGetDatumIsCalledThenExpectedExceptionIsThrown() throws DataMisconfigurationException {
        DateDatumDescription dateDatumDescription = new DateDatumDescription();
        dateDatumDescription.setName("name");
        dateDatumDescription.setConstant("24/06/1982");
        dateDatumDescription.setFrom("24/06/1982");

        dateDatumDescription.getDatum();
        fail("Expected exception was not thrown");
    }

    @Test(expected=DataMisconfigurationException.class)
    public void testGivenConstantIsNotNullAndToIsNotNullWhenGetDatumIsCalledThenExpectedExceptionIsThrown() throws DataMisconfigurationException {
        DateDatumDescription dateDatumDescription = new DateDatumDescription();
        dateDatumDescription.setName("name");
        dateDatumDescription.setConstant("24/06/1982");
        dateDatumDescription.setFrom("12/06/1982");
        dateDatumDescription.setTo("16/01/1994");

        dateDatumDescription.getDatum();
        fail("Expected exception was not thrown");
    }

    @Test
    public void testGivenAConstantValueContainingLocalDateAndTimeAndDateTimePatternIsSetToMatchDateAndTimeWhenGetDatumIsCalledThenConstantDateDatumIsReturnedWithTheGivenDate() throws DataMisconfigurationException {

        String constantValue = "2011-12-03 10:15:30";
        String dateTimePattern = "uuuu-MM-dd HH:mm:ss";

        DateDatumDescription dateDatumDescription = new DateDatumDescription();
        dateDatumDescription.setName("NAME");
        dateDatumDescription.setDateTimePattern(dateTimePattern);
        dateDatumDescription.setConstant(constantValue);

        Datum datum = dateDatumDescription.getDatum();

        assertThat(datum, instanceOf(ConstantDateDatum.class));
        ConstantDateDatum constantDateDatum = (ConstantDateDatum) datum;
        ZonedDateTime generatedDate = constantDateDatum.generate();

        assertThat(generatedDate.getDayOfMonth(), is(3));
        assertThat(generatedDate.getMonth(), is(Month.DECEMBER));
        assertThat(generatedDate.getYear(), is(2011));
        assertThat(generatedDate.getHour(), is(10));
        assertThat(generatedDate.getMinute(), is(15));
        assertThat(generatedDate.getSecond(), is(30));
    }

    @Test
    public void testGivenAConstantValueContainingDateTimeAndTimeZoneAndDateTimeFormatContainsTimeZoneWhenGetDatumIsCalledThenAConstantDateDatumIsReturnedThatGeneratesTheGivenDate() throws DataMisconfigurationException {

        String constantValue = "2011-12-03T10:15:30+1:00[Europe/Paris]";
        String dateTimePattern = "uuuu-MM-dd'T'HH:mm:ssZz";
        System.out.println(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");
        DateDatumDescription dateDatumDescription = new DateDatumDescription();
        dateDatumDescription.setName("NAME");
        dateDatumDescription.setDateTimePattern(dateTimePattern);
        dateDatumDescription.setConstant(constantValue);

        Datum datum = dateDatumDescription.getDatum();

        assertThat(datum, instanceOf(ConstantDateDatum.class));
        ConstantDateDatum constantDateDatum = (ConstantDateDatum) datum;
        ZonedDateTime generatedDate = constantDateDatum.generate();

        assertThat(generatedDate.getDayOfMonth(), is(3));
        assertThat(generatedDate.getMonth(), is(Month.DECEMBER));
        assertThat(generatedDate.getYear(), is(2011));
        assertThat(generatedDate.getHour(), is(10));
        assertThat(generatedDate.getMinute(), is(15));
        assertThat(generatedDate.getSecond(), is(30));
        assertThat(generatedDate.getOffset(), is(ZoneOffset.ofHours(-8)));
    }
}