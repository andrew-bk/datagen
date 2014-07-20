package org.ak.datagen.config;

/**
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="data",namespace="http://datagen.ak.org")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataDescription {

    @XmlElement(name="csv")
    private CSVConfig csvConfig;

    public void generateData() {
        // choose writer, Formatter and DataStructure

        /*
        In XSD you will be constrained to one of <csv>, <json> <xml> etc
         */
        // By definition, the CSV config will convert it's column configs to a TabularDataStructure

        // writer.write(formatter(datastructure)

    }



    public CSVConfig getCsvConfig() {
        return csvConfig;
    }

    public void setCsvConfig(CSVConfig csvConfig) {
        this.csvConfig = csvConfig;
    }

    @Override
    public String toString() {
        return "Config{" +
                "csvConfig=" + csvConfig +
                '}';
    }
}
