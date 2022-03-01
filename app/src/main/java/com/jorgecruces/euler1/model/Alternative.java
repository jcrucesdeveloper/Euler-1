package com.jorgecruces.euler1.model;

import java.util.Objects;

public class Alternative {
    private final int number;
    private final String value;

    public Alternative(int number, String value) {
        this.number = number;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, value);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
