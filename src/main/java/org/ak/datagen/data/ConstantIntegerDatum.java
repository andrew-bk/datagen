package org.ak.datagen.data;

/**
 */
public class ConstantIntegerDatum extends Datum {

    private int constantValue;

    public ConstantIntegerDatum(String name, int constantValue) {
        super(name);
        this.constantValue = constantValue;
    }

    @Override
    public Integer generate() {
        return this.constantValue;
    }
}
