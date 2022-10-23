package org.example.jackson.bench;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.openjdk.jmh.annotations.Benchmark;

public class BigIntegerJsonParseBench extends BenchmarkLauncher {

    static String test1000;
    static String test1000000;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            stringBuilder.append(7);
        }
        test1000 = stringBuilder.toString();
        for (int i = 1000; i < 1000000; i++) {
            stringBuilder.append(7);
        }
        test1000000 = stringBuilder.toString();
    }

    static ObjectMapper objectMapper = JsonMapper.builder()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .build();
    
    @Benchmark
    public void bigParse1000() throws Exception {
        objectMapper.readValue(genJson(test1000), ExtractFields.class);
    }

    @Benchmark
    public void bigParse1000000() throws Exception {
        objectMapper.readValue(genJson(test1000000), ExtractFields.class);
    }

    @Benchmark
    public void annotatedBigParse1000() throws Exception {
        objectMapper.readValue(genJson(test1000), AnnotatedExtractFields.class);
    }

    @Benchmark
    public void annotatedBigParse1000000() throws Exception {
        objectMapper.readValue(genJson(test1000000), AnnotatedExtractFields.class);
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
