package org.ak.datagen.data;

import java.util.Random;

/**
 */
public class StringRangeDatum extends Datum {

    private String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private Random random;
    private int maxLength;
    private int minLength;

    public StringRangeDatum(String name, int minLength, int maxLength) {
        super(name);
        this.random = new Random();

        this.maxLength = maxLength;
        this.minLength = minLength;
    }

    /**
     *
     * @return
     */
    @Override
    public String generate() {

        String value = "";

        int length = this.random.nextInt(maxLength - minLength + 1) + minLength;
        for(int i = 0; i < length; i++) {
            value += characters.charAt(random.nextInt(characters.length()));
        }
        return value;
    }

    protected void setRandom(Random random) {
        this.random = random;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }
}
