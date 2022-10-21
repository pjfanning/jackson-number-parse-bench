package org.example.jackson.bench;

import com.fasterxml.jackson.core.io.BigDecimalParser;
import org.openjdk.jmh.annotations.Benchmark;

import java.math.BigInteger;
import java.util.ArrayList;

public class BigIntegerParserBench extends BenchmarkLauncher {
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
    
    @Benchmark
    public void bigInt1000() throws Exception {
        new BigInteger(test1000);
    }

    @Benchmark
    public void bigInt1000000() throws Exception {
        new BigInteger(test1000000);
    }

    @Benchmark
    public void bigDec1000() throws Exception {
        BigDecimalParser.parse(test1000).toBigInteger();
    }

    @Benchmark
    public void bigDec1000000() throws Exception {
        BigDecimalParser.parse(test1000000).toBigInteger();
    }

    @Benchmark
    public void fastBigDec1000() throws Exception {
        FastBigDecimalParser.parse(test1000).toBigInteger();
    }

    @Benchmark
    public void fastBigDec1000000() throws Exception {
        FastBigDecimalParser.parse(test1000000).toBigInteger();
    }

}
