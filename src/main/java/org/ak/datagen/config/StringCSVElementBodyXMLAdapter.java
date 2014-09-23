package org.ak.datagen.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 *
 */
public class StringCSVElementBodyXmlAdapter extends XmlAdapter<String, Set<String>> {

    @Override
    public Set<String> unmarshal(String str) throws Exception {
        return str.length() > 0
                ? Arrays.stream(str.split(","))
                    .map(String::trim)
                    .collect(Collectors.toSet())
                : new HashSet<String>();
    }

    @Override
    public String marshal(Set<String> set) throws Exception {
        return set.stream()
                .collect(joining(",", "", ","));
    }
}
