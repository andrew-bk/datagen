package org.ak.datagen.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 */
public class CSVElementBodyXmlAdapter extends XmlAdapter<String, Set<Integer>> {

    @Override
    public Set<Integer> unmarshal(String str) throws Exception {
        return Arrays.asList(str.split(",")).stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toSet());
    }

    @Override
    public String marshal(Set<Integer> set) throws Exception {
        return set.stream()
                .map(i -> i.toString())
                .collect(joining(",", "", ","));
    }
}
