package org.ak.datagen.data;

import java.util.Random;

/**
 */
public class IntegerRangeDatum extends Datum {

    private int min;
    private int max;

    private Random random;

    /**
     *
     * @param name
     * @param min (inclusive)
     * @param max (inclusive)
     */
    public IntegerRangeDatum(String name, int min, int max) {
        super(name);

        if(max < min) {
            throw new IllegalArgumentException("Maximum must be greater than minimum");
        }
        this.min = min;
        this.max = max;

        this.random = new Random();
    }

    public Integer generate() {
        if(min == max) {
            return max;
        }

        int range = max - min + 1;

        return random.nextInt(range) + min;
    }

    @Override
    public String toString() {
        return "IntegerRangeDatum{" +
                "name='" + getName()  + '\'' +
                ", max=" + max +
                ", min=" + min +
                '}';
    }

    /**
     * This setter allows for mocking of the Random class
     * @param random
     */
    protected void setRandom(Random random) {
        this.random = random;
    }
}
