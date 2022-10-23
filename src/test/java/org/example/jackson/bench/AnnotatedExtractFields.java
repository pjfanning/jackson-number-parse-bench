package org.example.jackson.bench;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnnotatedExtractFields {
    private final String s;
    private final int i;

    public AnnotatedExtractFields() {
        this.s = null;
        this.i = -1;
    }

    public AnnotatedExtractFields(String s, int i) {
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
