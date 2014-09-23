package org.ak.datagen.config;

import org.ak.datagen.data.ConstantDateDatum;
import org.ak.datagen.data.Datum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Locale;
import java.util.Optional;

/**
 * the expected format of constant is the default for your locale, which we will allow to be overriden by a global vairable
 displayFormat is optional and defaults to the default for your locale
 */
public class DateDatumDescription implements DatumDescription {

    private String name;
    private String constant;
    private String displayFormat;
    private String from;
    private String to;

    // The pattern used to describe dates in the xml, will eventually be user configurable
    private String dateTimePattern = "dd/MM/uuuu";

    public String getDateTimePattern() {
        return dateTimePattern;
    }

    public void setDateTimePattern(String dateTimePattern) {
        this.dateTimePattern = dateTimePattern;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public String getDisplayFormat() {
        return displayFormat;
    }

    public void setDisplayFormat(String displayFormat) {
        this.displayFormat = displayFormat;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Datum getDatum() throws DataMisconfigurationException {

        DateTimeFormatter dateTimePatternDateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);

        DateTimeFormatter displayFormatDateTimeFormatter =
                displayFormat == null
                        ? DateTimeFormatter.ofPattern(dateTimePattern, Locale.getDefault())
                        : DateTimeFormatter.ofPattern(displayFormat);

        Datum datum = null;

        if(constant != null && from == null && to == null) {

            try {

                TemporalAccessor temporalAccessor = dateTimePatternDateTimeFormatter.parseBest(this.constant, ZonedDateTime::from, LocalDateTime::from, LocalDate::from);

                if(temporalAccessor instanceof LocalDate) {
                    return new ConstantDateDatum(this.name, LocalDate.from(temporalAccessor), displayFormatDateTimeFormatter);
                } else if(temporalAccessor instanceof LocalDateTime) {
                    return new ConstantDateDatum(this.name, LocalDateTime.from(temporalAccessor), displayFormatDateTimeFormatter);
                }

            } catch(DateTimeParseException ex) {
                ex.printStackTrace();
                throw new DataMisconfigurationException("Could not create datum: " + ex.getMessage());
            }

        } else if(from != null && to != null && constant == null) {
            //range
        } else {
            throw new DataMisconfigurationException(getName() + " is misconfigured.  A date must be configured as follows: " + printUsage());
        }

        return datum;





    }

    /**
     * Gives a user-friendly message of how to configure the integer tag
     */
    private String printUsage() {
        return  "EITHER\n" +
                "  attributes:\n" +
                "    'constant': generates a constant date\n" +
                "    OR\n" +
                "    'from' AND 'to': generates a date in the given range\n";
    }
}
