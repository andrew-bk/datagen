package org.ak.datagen.data;

/**
 */
public class IntegerSequenceDatum extends Datum {

    private int start;
    private int sequenceValue;
    private int step;

    public IntegerSequenceDatum(String name, int start, int step) {
        super(name);
        if(step == 0) {
            throw new IllegalArgumentException("Step must not be zero");
        }
        this.start = start;
        this.sequenceValue = start;
        this.step = step;
    }

    @Override
    public Integer generate() {
        int returnVal = this.sequenceValue;
        this.sequenceValue += step;
        return returnVal;
    }

    public int getStart() {
        return start;
    }

    public int getStep() {
        return step;
    }
}
