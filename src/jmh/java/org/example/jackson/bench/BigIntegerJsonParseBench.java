package org.example.jackson.bench;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class BigIntegerJsonParseBench extends BenchmarkLauncher {

    static String test1000;
    static String test1000000;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            stringBuilder.append(7);
        }
        test1000 = genJson(stringBuilder.toString());
        for (int i = 1000; i < 1000000; i++) {
            stringBuilder.append(7);
        }
        test1000000 = genJson(stringBuilder.toString());
    }

    private static final ObjectMapper objectMapper = JacksonUtil.objectMapper;
    
    @Benchmark
    public void bigParse1000(Blackhole blackhole) throws Exception {
        blackhole.consume(objectMapper.readValue(test1000, ExtractFields.class));
    }

    @Benchmark
    public void bigParse1000000(Blackhole blackhole) throws Exception {
        blackhole.consume(objectMapper.readValue(test1000000, ExtractFields.class));
    }

    @Benchmark
    public void annotatedBigParse1000(Blackhole blackhole) throws Exception {
        blackhole.consume(objectMapper.readValue(test1000, AnnotatedExtractFields.class));
    }

    @Benchmark
    public void annotatedBigParse1000000(Blackhole blackhole) throws Exception {
        blackhole.consume(objectMapper.readValue(test1000000, AnnotatedExtractFields.class));
    }

    private static String genJson(String num) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("{\"s\":\"s\",\"n\":")
                .append(num)
                .append(",\"i\":1}");
        return stringBuilder.toString();
    }
}
