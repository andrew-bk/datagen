package org.ak.datagen.format;

import org.ak.datagen.structure.TabularDataStructure;

/**
 */
public class RelationalDataFormatter implements TabularFormatter {

    //  rather than return an object there will be a custom class that encapsulates a record in a database table
    public Object getNextRecord() {
        return null;
    }

    @Override
    public void format(TabularDataStructure tabularDataStructure) {

    }
}
