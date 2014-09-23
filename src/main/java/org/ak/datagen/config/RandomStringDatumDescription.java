package org.ak.datagen.config;

import org.ak.datagen.data.Datum;
import org.ak.datagen.data.StringRangeDatum;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RandomStringDatumDescription implements DatumDescription {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private int minLength;
    @XmlAttribute
    private int maxLength;

    public void setName(String name) {
        this.name = name;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Datum getDatum() throws DataMisconfigurationException {
        return new StringRangeDatum(name, minLength, maxLength);
    }
}
