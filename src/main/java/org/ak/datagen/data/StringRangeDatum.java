package org.ak.datagen.data;

import java.util.Random;

/**
 */
public class StringRangeDatum extends Datum {

    private String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private Random random;
    private int max;
    private int min;

    public StringRangeDatum(String name, int min, int max) {
        super(name);
        this.random = new Random();

        this.max = max;
        this.min = min;
    }

    /**
     *
     * @return
     */
    @Override
    public String generate() {

        String value = "";

        int length = this.random.nextInt(max - min + 1) + min;
        for(int i = 0; i < length; i++) {
            value += characters.charAt(random.nextInt(characters.length()));
        }
        return value;
    }

    protected void setRandom(Random random) {
        this.random = random;
    }
}
