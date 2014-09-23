package org.ak.datagen.data;

import java.util.Random;

/**
 */
public class IntegerRangeDatum extends Datum {

    private int from;
    private int to;

    private Random random;

    /**
     *
     * @param name
     * @param from (inclusive)
     * @param to (inclusive)
     */
    public IntegerRangeDatum(String name, int from, int to) {
        super(name);

        if(to < from) {
            throw new IllegalArgumentException("'to' must be greater than 'from'");
        }
        this.from = from;
        this.to = to;

        this.random = new Random();
    }

    public Integer generate() {
        if(from == to) {
            return to;
        }

        int range = to - from + 1;

        return random.nextInt(range) + from;
    }

    public int getFrom() {
        return this.from;
    }

    public int getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "IntegerRangeDatum{" +
                "name='" + getName()  + '\'' +
                ", from=" + from +
                ", to=" + to +
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
