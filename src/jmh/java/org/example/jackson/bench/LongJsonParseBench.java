package org.example.jackson.bench;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

@OutputTimeUnit(TimeUnit.SECONDS)
public class LongJsonParseBench {

    private static final JsonFactory JSON_FACTORY = new JsonFactory();

    private static final String JSON;

    static {
        StringBuilder json = new StringBuilder();
        Random random = new Random(0L);
        json.append('[');
        for (int i = 0; i < 1_000; i++) {
            if (i > 0) {
                json.append(',');
            }
            json.append(random.nextLong());
        }
        json.append(']');
        JSON = json.toString();
    }

    @Benchmark
    public void parseLong(Blackhole blackhole) throws IOException {
        try (JsonParser parser = JSON_FACTORY.createParser(JSON)) {
            parser.nextToken();

            JsonToken nextToken = parser.nextToken();
            while (nextToken == JsonToken.VALUE_NUMBER_INT) {
                blackhole.consume(parser.getLongValue());
                nextToken = parser.nextToken();
            }
        }
    }

}
