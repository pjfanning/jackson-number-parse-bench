package org.example.jackson.bench;

import com.fasterxml.jackson.core.io.BigDecimalParser;
import org.example.jackson.bench.doubleparser.JavaBigIntegerParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

import java.math.BigInteger;

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
    public void bigInt1000(Blackhole blackhole) throws Exception {
        blackhole.consume(new BigInteger(test1000));
    }

    @Benchmark
    public void bigInt1000000(Blackhole blackhole) throws Exception {
        blackhole.consume(new BigInteger(test1000000));
    }

    @Benchmark
    public void bigDec1000(Blackhole blackhole) throws Exception {
        blackhole.consume(BigDecimalParser.parse(test1000).toBigInteger());
    }

    @Benchmark
    public void bigDec1000000(Blackhole blackhole) throws Exception {
        blackhole.consume(BigDecimalParser.parse(test1000000).toBigInteger());
    }

    @Benchmark
    public void fastBigDec1000(Blackhole blackhole) throws Exception {
        blackhole.consume(FastBigDecimalParser.parse(test1000).toBigInteger());
    }

    @Benchmark
    public void fastBigDec1000000(Blackhole blackhole) throws Exception {
        blackhole.consume(FastBigDecimalParser.parse(test1000000).toBigInteger());
    }

    @Benchmark
    public void javaBigIntegerParser1000(Blackhole blackhole) throws Exception {
        blackhole.consume(JavaBigIntegerParser.parseBigIntegerOrNull(test1000));
    }

    @Benchmark
    public void javaBigIntegerParser1000000(Blackhole blackhole) throws Exception {
        blackhole.consume(JavaBigIntegerParser.parseBigIntegerOrNull(test1000000));
    }

}
