package org.example.jackson.bench;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.commons.io.IOUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class LongParserBench extends BenchmarkLauncher {

    private static final int LEN = 1000;
    private static final String[] LONGS = new String[LEN];

    static {
        Random random = new Random(0L);
        for (int i = 0; i < LEN; i++) {
            LONGS[i] = Long.toString(random.nextLong());
        }
    }

    @Benchmark
    public void existingJacksonParse(Blackhole blackhole) throws Exception {
        for (int i = 0; i < LEN; i++) {
            blackhole.consume(NumberUtil.jacksonParseLong(LONGS[i]));
        }
    }

    @Benchmark
    public void alternateJacksonParseSimplified(Blackhole blackhole) {
        for (int i = 0; i < LEN; i++) {
            blackhole.consume(NumberUtil.alternateParseSimplified(LONGS[i]));
        }
    }
    
    @Benchmark
    public void alternateJacksonParseSwitch(Blackhole blackhole) {
        for (int i = 0; i < LEN; i++) {
            blackhole.consume(NumberUtil.alternateParseSwitch(LONGS[i]));
        }
    }

    @Benchmark
    public void javaParse(Blackhole blackhole) throws Exception {
        for (int i = 0; i < LEN; i++) {
            blackhole.consume(Long.parseLong(LONGS[i]));
        }
    }
}
