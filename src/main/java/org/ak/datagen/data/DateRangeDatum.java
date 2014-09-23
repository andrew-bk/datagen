package org.ak.datagen.data;

import org.ak.datagen.config.DataMisconfigurationException;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Random;

/**
 * Generates a random date within a specified range (inclusive)
 */
public class DateRangeDatum extends Datum {

    private ZonedDateTime from;
    private ZonedDateTime to;
    private DateTimeFormatter displayFormat;

    private Random random;

    public DateRangeDatum(String name, ZonedDateTime from, ZonedDateTime to) throws DataMisconfigurationException {
        this(name, from, to, DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }

    public DateRangeDatum(String name, ZonedDateTime from, ZonedDateTime to, DateTimeFormatter displayFormat) throws DataMisconfigurationException {
        super(name);

        if(from.isAfter(to)) {
            throw new DataMisconfigurationException("The to date must be after the from date");
        } else if(from.until(to, ChronoUnit.DAYS) == 0) {
            throw new DataMisconfigurationException("The to date must not be the same as the from date. To generate a constant date use the 'constant' attribute or add one day to the 'to' date");
        }

        this.from = from;
        this.to = to;
        this.displayFormat = displayFormat;
        this.random = new Random();
    }

    @Override
    public ZonedDateTime generate() {
        return from.plusDays(random.nextLong() % from.until(to, ChronoUnit.DAYS));
    }

    public String format() {
        return generate().format(displayFormat);
    }

    protected void setRandom(Random random) {
        this.random = random;
    }
}
