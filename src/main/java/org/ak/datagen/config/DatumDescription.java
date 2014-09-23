package org.ak.datagen.config;

import org.ak.datagen.data.Datum;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 */
public interface DatumDescription {

    public String getName();

    public Datum getDatum() throws DataMisconfigurationException;
}
