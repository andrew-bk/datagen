package org.ak.datagen.config;

import org.ak.datagen.data.Datum;
import org.ak.datagen.data.IntegerSequenceDatum;
import org.ak.datagen.data.IntegerSetDatum;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SequenceDatumDescription implements DatumDescription {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private Integer start;
    @XmlAttribute
    private Integer step;


    public SequenceDatumDescription() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Datum getDatum() throws DataMisconfigurationException {
        return new IntegerSequenceDatum(getName(), start == null ? 1 : start, step == null ? 1 : step);
    }
}
