package org.ak.datagen.data;

import org.ak.datagen.config.DataMisconfigurationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.text.Format;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DateRangeDatumTest {

    @Mock
    Random randomMock;

    @Test(expected=DataMisconfigurationException.class)
    public void testGivenAFromDateThatIsTheSameAsTheToDateWhenGenerateIsCalledThenExpectedExceptionIsThrown() throws DataMisconfigurationException {

        ZonedDateTime expectedDate = ZonedDateTime.now();

        DateRangeDatum dateRangeDatum = new DateRangeDatum("name", expectedDate, expectedDate);
        fail("DataMisconfigurationException was not thrown");
    }

    @Test(expected=DataMisconfigurationException.class)
    public void testGivenAFromDateThatIsAfterTheToDateWhenDateRangeDatumIsConstructedThenExpectedExceptionIsThrown() throws DataMisconfigurationException {
        ZonedDateTime from = ZonedDateTime.now().plusDays(10);
        ZonedDateTime to = ZonedDateTime.now();

        DateRangeDatum dateRangeDatum = new DateRangeDatum("name", from, to);
        fail("DataMisconfigurationException was not thrown");
    }

    @Test
    public void testThatWhenADateRangeDatumIsConstructedThenTheNameIsSetCorrectly() throws DataMisconfigurationException {
        String expectedName = "expectedName";
        DateRangeDatum dateRangeDatum = new DateRangeDatum(expectedName, ZonedDateTime.now(), ZonedDateTime.now().plusDays(1));
        assertThat(dateRangeDatum.getName(), is(expectedName));
    }

    @Test
    public void testGivenADateRangeDatumWhenTheRandomNumberGeneratorReturnsTheLowerBoundThenGenerateReturnsADateEqualToTheGivenFromDate() throws DataMisconfigurationException {
        ZonedDateTime fromDate = ZonedDateTime.now().minusDays(3);
        ZonedDateTime toDate = ZonedDateTime.now();

        DateRangeDatum dateRangeDatum = new DateRangeDatum("name", fromDate, toDate);
        dateRangeDatum.setRandom(randomMock);
        when(randomMock.nextLong()).thenAnswer(RandomUtil.WITH_LOWER_BOUND_OF_LONG_RANDOM_RANGE);
    }

    @Test
    public void testGivenADateRangeDatumWhenTheRandomNumberGeneratorReturnsTheUpperBoundThenGenerateReturnsADateEqualToTheGivenToDate() throws DataMisconfigurationException {

        final ZonedDateTime now = ZonedDateTime.now();
        final ZonedDateTime fromDate = now.minusDays(3);
        final ZonedDateTime toDate = now;
        System.out.println(fromDate.until(toDate, ChronoUnit.DAYS));

        DateRangeDatum dateRangeDatum = new DateRangeDatum("name", fromDate, toDate);
        dateRangeDatum.setRandom(randomMock);
        when(randomMock.nextLong()).thenAnswer(new Answer<Long>() {

            @Override
            public Long answer(InvocationOnMock invocationOnMock) throws Throwable {
                return fromDate.until(toDate, ChronoUnit.DAYS) - 1;
            }
        });

        assertThat(dateRangeDatum.generate(), is(toDate.minusDays(1)));
    }

    @Test
    public void testWhenDateRangeDautmIsConstructedWithNoDateTimeFormatterThenTheDefaultDateTimeFormatterForTheLocaleIsUsedWithTheSHORTFormatStyle() throws DataMisconfigurationException {
        ZonedDateTime from = ZonedDateTime.now();
        ZonedDateTime to = from.plusDays(3);

        DateRangeDatum dateRangeDatum = new DateRangeDatum("name", from, to);

        DateTimeFormatter format = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        String formattedDate = dateRangeDatum.format();

        try {

            LocalDateTime result = LocalDateTime.parse(formattedDate, format);

        } catch(DateTimeParseException e) {
            fail("Formatted date does not match expected format. " + e.getMessage() + " Formatted date " + formattedDate + ". Format " + format.toFormat());
        }
    }
}