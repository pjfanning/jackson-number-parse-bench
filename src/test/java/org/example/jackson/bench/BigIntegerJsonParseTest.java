package org.example.jackson.bench;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.StreamReadConstraints;
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

    @Test
    public void bigParse() throws Exception {
        ExtractFields ef = JacksonUtil.objectMapper.readValue(genJson(test1000000), ExtractFields.class);
    }

    @Test
    public void bigParseNoDefaultConstructor() throws Exception {
        ExtractFieldsNoDefaultConstructor ef =
                JacksonUtil.objectMapper.readValue(genJson(test1000000), ExtractFieldsNoDefaultConstructor.class);
    }

    @Test
    public void annotatedBigParse() throws Exception {
        AnnotatedExtractFields ef = JacksonUtil.objectMapper.readValue(
                genJson(test1000000), AnnotatedExtractFields.class);
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
