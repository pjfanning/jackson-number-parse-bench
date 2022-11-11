package org.example.jackson.bench;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;

public class BigIntegerJsonParseTest {

    static String test1000000;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            stringBuilder.append(7);
        }
        test1000000 = stringBuilder.toString();
    }

    static ObjectMapper objectMapper = JsonMapper.builder()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .build();

    @Test
    public void bigParse1000() throws Exception {
        ExtractFields ef = objectMapper.readValue(genJson(test1000000), ExtractFields.class);
    }

    @Test
    public void annotatedBigParse1000() throws Exception {
        AnnotatedExtractFields ef = objectMapper.readValue(genJson(test1000000), AnnotatedExtractFields.class);
    }

    private String genJson(String num) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("{\"s\":\"s\",\"n\":")
                .append(num)
                .append(",\"i\":1}");
        return stringBuilder.toString();
    }
}
