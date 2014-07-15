package org.ak.datagen.data;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Randomly generates string constants from a given set of Strings
 */
public class StringSetDatum extends Datum {

    private Random random;
    private Set<String> set;

    public StringSetDatum(String name, Set<String> set) {
        super(name);
        if(set == null) {
            throw new IllegalArgumentException("Set must not be null");
        }
        if(set.isEmpty()) {
            throw new IllegalArgumentException("Set must not be empty");
        }
        this.set = set;
        this.random = new Random();
    }

    @Override
    public String generate() {

        int randomElementIndex = random.nextInt(set.size());
        Iterator<String> itr = set.iterator();
        String chosenString = itr.next();
        for(int i = 0; i < randomElementIndex; i++) {
            chosenString = itr.next();
        }
        return chosenString;
    }

    protected void setRandom(Random random) {
        this.random = random;
    }
}
