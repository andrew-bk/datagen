package org.ak.datagen.config;

/**
 * Indicates that a mistake has been made in the configuration of a data type.
 */
public class DataMisconfigurationException extends Exception {

    public DataMisconfigurationException(String message) {
        super(message);
    }
}
