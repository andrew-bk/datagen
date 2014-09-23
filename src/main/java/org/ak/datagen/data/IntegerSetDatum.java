package org.ak.datagen.data;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 */
public class IntegerSetDatum extends Datum {

    private Set<Integer> set;
    private Random random;

    public IntegerSetDatum(String name, Set<Integer> set) {
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
    public Integer generate() {
        int randomElementIndex = random.nextInt(set.size());
        Iterator<Integer> itr = set.iterator();
        Integer chosenInteger = itr.next();
        for(int i = 0; i < randomElementIndex; i++) {
            chosenInteger = itr.next();
        }
        return chosenInteger;
    }

    protected void setRandom(Random random) {
        this.random = random;
    }

    public Set<Integer> getSet() {
        return this.set;
    }
}
