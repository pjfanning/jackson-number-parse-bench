package org.example.jackson.bench;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractFieldsNoDefaultConstructor {
    private final String s;
    private final int i;

    @JsonCreator
    public ExtractFieldsNoDefaultConstructor(@JsonProperty("s") String s, @JsonProperty("i") int i) {
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
