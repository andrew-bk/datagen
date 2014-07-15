package org.ak.datagen.data;

/**
 * TODO
 *
 * date range
 *
 * Nullability, allow for empty or null values
 *
 * Unique non sequential - e.g. a transaction id that may not be in sequential order but must be unique
 */
public abstract class Datum {

    private String name;

    protected Datum(String name) {
        if(name == null) {
            throw new IllegalArgumentException("name must not be null");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Datum{" +
                "name='" + name + '\'' +
                '}';
    }

    public abstract Object generate();

}
