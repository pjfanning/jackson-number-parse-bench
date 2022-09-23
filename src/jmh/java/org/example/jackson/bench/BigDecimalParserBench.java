package org.example.jackson.bench;

import com.fasterxml.jackson.core.io.BigDecimalParser;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.ArrayList;

public class BigDecimalParserBench extends BenchmarkLauncher {
    static ArrayList<String> values;
    
    static {
        values = new ArrayList<>();
        values.add("123");
        values.add("-123");
        values.add("123.456");
        values.add("-123.456");
        values.add("12345678900987654321");
        values.add("-12345678900987654321");
        values.add("1234567890.0987654321");
        values.add("-1234567890.0987654321");
        values.add("1.23456789E-3");
        values.add("1234567890.0987654321E-127");
        values.add("1E+3");
        values.add("1E-3");
        values.add("-1E+3");
        values.add("-1E-3");
    }
    
    @Benchmark
    public void currentParser() throws Exception {
        for (String s : values) {
            BigDecimalParser.parse(s);
        }
    }

    @Benchmark
    public void fastParser() throws Exception {
        for (String s : values) {
            FastBigDecimalParser.parse(s);
        }
    }
}