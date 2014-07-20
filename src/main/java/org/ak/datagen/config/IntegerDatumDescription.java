package org.ak.datagen.config;

import org.ak.datagen.data.Datum;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import java.util.Set;

/**
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class IntegerDatumDescription  {

    @XmlAttribute
    private Integer constant;
    @XmlAttribute
    private Integer from;
    @XmlAttribute
    private Integer to;

    @XmlJavaTypeAdapter(CSVElementBodyXmlAdapter.class)
    //@XmlList
    @XmlValue
    private Set<Integer> set;

    @XmlAttribute
    protected String name;

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

   // @Override
    public Datum getDatum() {
        return null;
    }
}
