package org.ak.datagen.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 */
public class DecimalRangeDatum extends Datum {

    private Random random;
    private int min;
    private int max;
    private int numDecimalPlaces;

    /**
     * Generates decimal values in a given range.
     *
     * @param name
     * @param numDecimalPlaces all values generated by this class will have this many decimal places
     */
    public DecimalRangeDatum(String name, int min, int max, int numDecimalPlaces) {
        super(name);
        this.min = min;
        this.max = max;
        this.numDecimalPlaces = numDecimalPlaces;
        this.random = new Random();
    }

    @Override
    public BigDecimal generate() {

        int range = max - min;
        double randomDoubleInRange = (range * random.nextDouble()) + min;

        return new BigDecimal(randomDoubleInRange).setScale(this.numDecimalPlaces, RoundingMode.HALF_UP);
    }

    protected void setRandom(Random random) {
        this.random = random;
    }
}
