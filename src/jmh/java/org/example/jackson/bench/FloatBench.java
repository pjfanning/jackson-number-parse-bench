package org.example.jackson.bench;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.commons.io.IOUtils;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FloatBench extends BenchmarkLauncher {

    private static String ARRAY_TEXT;
    private static ObjectMapper MAPPER;

    static {
        try {
           JsonFactory factory = JsonFactory.builder().enable(StreamReadFeature.USE_FAST_DOUBLE_PARSER).build();
           MAPPER = JsonMapper.builder(factory).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try (InputStream stream = FloatBench.class.getResourceAsStream("/float-array.txt")) {
            ARRAY_TEXT = IOUtils.toString(stream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    public void bigArrayOfFloatPrimitives() throws Exception {
        float[] floats = MAPPER.readValue(ARRAY_TEXT, float[].class);
    }
}
