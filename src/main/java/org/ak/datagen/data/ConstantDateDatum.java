package org.ak.datagen.data;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

/**
 */
public class ConstantDateDatum extends Datum {

    private ZonedDateTime date;
    private DateTimeFormatter displayFormat;

    public ConstantDateDatum(String name, LocalDate date, DateTimeFormatter displayFormat) {
        this(name, date.atStartOfDay(), displayFormat);
    }

    public ConstantDateDatum(String name, LocalDateTime date, DateTimeFormatter displayFormat) {
        this(name, date.atZone(ZoneId.systemDefault()), displayFormat);
    }

    public ConstantDateDatum(String name, ZonedDateTime date, DateTimeFormatter displayFormat) {
        super(name);
        this.date = date;
        this.displayFormat = displayFormat;
    }

    @Override
    public ZonedDateTime generate() {
        return this.date;
    }

    public String format() {
        return generate().format(displayFormat);
    }
}
