package org.ak.datagen.data;

import java.util.Date;

/**
 */
public class ConstantDateDatum extends Datum {

    private Date date;

    public ConstantDateDatum(String name, Date date) {
        super(name);
        this.date = date;
    }

    @Override
    public Date generate() {
        return this.date;
    }
}
