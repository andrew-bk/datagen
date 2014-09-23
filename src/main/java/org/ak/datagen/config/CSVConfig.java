package org.ak.datagen.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates the XML configuration for generating CSV data
 */
public class CSVConfig {

    List<DatumDescription> datumDescriptions;
    private String file;
    private String delimiter;

    public CSVConfig() {
        this.delimiter = ",";
        this.datumDescriptions = new ArrayList<>();
    }

    public String getFile() {
        return file;
    }

    public List<DatumDescription> getDatumDescriptions() {
        return datumDescriptions;
    }

    @XmlElement(name="integer")
    public void setIntegerDatumDescription(IntegerDatumDescription integerDatum) {
        datumDescriptions.add(integerDatum);
    }

    @XmlElement(name="sequence")
    public void setSequenceDatumDescription(SequenceDatumDescription sequenceDatumDescription) {
        datumDescriptions.add(sequenceDatumDescription);
    }

    @XmlElement(name="text")
    public void setStringDatumDescription(StringDatumDescription stringDatumDescription) {
        datumDescriptions.add(stringDatumDescription);
    }

    @XmlElement(name="randomText")
    public void setRandomStringDatumDescription(RandomStringDatumDescription randomStringDatumDescription) {
        datumDescriptions.add(randomStringDatumDescription);
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
                ", numbers=" + datumDescriptions +
                '}';
    }
}
