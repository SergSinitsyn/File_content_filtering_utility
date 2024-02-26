package org.serg_sinitsyn.file_content_filtering_utility;

public enum DataType {
    INTEGER_NUMBER, REAL_NUMBER, STRING;

    @Override
    public String toString() {
        switch (this) {
            case INTEGER_NUMBER:
                return "integers";
            case REAL_NUMBER:
                return "floats";
            case STRING:
                return "strings";
            default:
                throw new IllegalArgumentException();
        }
    }
}