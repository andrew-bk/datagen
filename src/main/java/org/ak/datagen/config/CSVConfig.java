package org.ak.datagen.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * This class encapsulates the XML configuration for generating CSV data
 */
public class CSVConfig {

    List<IntegerDatumDescription> integerDatumDescriptions;
    private String file;
    private String delimiter;

    public CSVConfig() {
        this.delimiter = ",";
    }

    public String getFile() {
        return file;
    }

    public List<IntegerDatumDescription> getIntegerDatumDescriptions() {
        return integerDatumDescriptions;
    }

    @XmlElement(name="integer")
    public void setIntegerDatumDescriptions(List<IntegerDatumDescription> integerDatumDescriptions) {
        this.integerDatumDescriptions = integerDatumDescriptions;
    }

    @XmlAttribute
    public void setFile(String file) {
        this.file = file;
    }

    public String getDelimiter() {
        return delimiter;
    }

    @XmlAttribute
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public String toString() {
        return "CSVConfig{" +
                "file='" + file + '\'' +
                ", delimiter='" + delimiter + '\'' +
                ", numbers=" + integerDatumDescriptions +
                '}';
    }
}
