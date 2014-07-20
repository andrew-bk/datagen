package org.ak.datagen.config;

import org.ak.datagen.data.Datum;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class DatumDescription {


    @XmlAttribute
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Datum getDatum();
}
