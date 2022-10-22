package org.example.jackson.bench;

public class ExtractFields {
    private final String s;
    private final int i;

    public ExtractFields() {
        this.s = null;
        this.i = -1;
    }

    public ExtractFields(String s, int i) {
        this.s = s;
        this.i = i;
    }

    public String getS() {
        return s;
    }

    public int getI() {
        return i;
    }
}
