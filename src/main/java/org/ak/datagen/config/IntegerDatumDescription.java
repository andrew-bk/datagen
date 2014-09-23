package org.ak.datagen.config;

import org.ak.datagen.data.ConstantIntegerDatum;
import org.ak.datagen.data.Datum;
import org.ak.datagen.data.IntegerRangeDatum;
import org.ak.datagen.data.IntegerSetDatum;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Set;

/**
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class IntegerDatumDescription implements DatumDescription {

    @XmlAttribute
    private Integer constant;
    @XmlAttribute
    private Integer from;
    @XmlAttribute
    private Integer to;

    @XmlJavaTypeAdapter(IntegerCSVElementBodyXmlAdapter.class)
    @XmlValue
    private Set<Integer> set;

    @XmlAttribute
    protected String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Integer> getSet() {
        return set;
    }

    public void setSet(Set<Integer> set) {
        this.set = set;
    }

    public Integer getConstant() {
        return constant;
    }

    public void setConstant(Integer constant) {
        this.constant = constant;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public String toString() {
        return "IntegerDatumDescription{" +
                "name='" +  '\'' +
                ", constant=" + constant +
                ", from=" + from +
                ", to=" + to +
                '}';
    }

    @Override
    public Datum getDatum() throws DataMisconfigurationException {

        if(constant == null && from == null && to == null && set == null) {
            throw new DataMisconfigurationException(getName() + " is missing it's configuration.\n" + printUsage());
        } else if(constant != null) {
            if(from != null || to != null || set != null) {
                throw new DataMisconfigurationException(getName() + " is misconfigured. An integer should be configured as follows:\n" + printUsage());
            }
            return new ConstantIntegerDatum(getName(), constant);
        } else if(from != null && to != null) {
            if(set != null) {
                throw new DataMisconfigurationException(getName() + " is misconfigured. An integer should be configured as follows:\n" + printUsage());
            }
            return new IntegerRangeDatum(getName(), from, to);
        } else  {
            if(from != null || to != null) {
                throw new DataMisconfigurationException(getName() + " is misconfigured. An integer should be configured as follows:\n" + printUsage());
            }
            return new IntegerSetDatum(getName(), set);
        }
    }

    /**
     * Gives a user-friendly message of how to configure the integer tag
     */
    public String printUsage() {
        return "EITHER\n" +
               "  attributes:\n" +
               "    'constant': generates a constant value\n" +
               "    OR\n" +
               "    'from' AND 'to': generates a integer in the given range (inclusive)\n" +
               "OR\n" +
               "  tag body:\n" +
               "    must contain a comma-separated list of unique integers, randomly generates integers from the given set";
    }
}
