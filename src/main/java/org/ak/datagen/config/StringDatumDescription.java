package org.ak.datagen.config;

import org.ak.datagen.data.ConstantStringDatum;
import org.ak.datagen.data.Datum;
import org.ak.datagen.data.StringSetDatum;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Set;

/**
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class StringDatumDescription implements DatumDescription {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private String constant;
    @XmlJavaTypeAdapter(StringCSVElementBodyXmlAdapter.class)
    @XmlValue
    private Set<String> set;

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Datum getDatum() throws DataMisconfigurationException {
        if((constant != null && set != null) ||
           (constant == null && set == null)) {
            throw new DataMisconfigurationException(getName() + " is misconfigured.\n" + printUsage());
        }
        if(set != null) {
            return new StringSetDatum(getName(), set);
        } else {
            return new ConstantStringDatum(getName(), constant);
        }
    }

    public String printUsage() {
        return "EITHER\n" +
                "  attributes:\n" +
                "    'constant': generates a constant value\n" +
                "OR\n" +
                "  tag body:\n" +
                "    must contain a comma-separated list of unique strings, randomly generates strings from the given set";
    }
}
