package org.ak.datagen.data;

/**
 */
public class ConstantStringDatum extends Datum {

    private String constantValue;

    public ConstantStringDatum(String name, String constantValue) {
        super(name);
        if(constantValue == null) {
            throw new IllegalArgumentException("constantValue must not be null");
        }
        this.constantValue = constantValue;
    }

    @Override
    public String generate() {
        return this.constantValue;
    }

    public String getConstantValue() {
        return this.constantValue;
    }
}
