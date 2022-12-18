package org.example.jackson.bench;

import ch.randelshofer.fastdoubleparser.JavaDoubleParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class DoubleParserBench extends BenchmarkLauncher {
    static String test10;
    static String test1000;
    static String test1000000;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(7);
        }
        test10 = stringBuilder.toString();
        for (int i = 10; i < 1000; i++) {
            stringBuilder.append(7);
        }
        test1000 = stringBuilder.toString();
        for (int i = 1000; i < 1000000; i++) {
            stringBuilder.append(7);
        }
        test1000000 = stringBuilder.toString();
    }

    @Benchmark
    public void double10(Blackhole blackhole) throws Exception {
        blackhole.consume(Double.parseDouble(test10));
    }

    @Benchmark
    public void double1000(Blackhole blackhole) throws Exception {
        blackhole.consume(Double.parseDouble(test1000));
    }

    @Benchmark
    public void double1000000(Blackhole blackhole) throws Exception {
        blackhole.consume(Double.parseDouble(test1000000));
    }

    @Benchmark
    public void fastDouble10(Blackhole blackhole) throws Exception {
        blackhole.consume(JavaDoubleParser.parseDouble(test10));
    }

    @Benchmark
    public void fastDouble1000(Blackhole blackhole) throws Exception {
        blackhole.consume(JavaDoubleParser.parseDouble(test1000));
    }

    @Benchmark
    public void fastDouble1000000(Blackhole blackhole) throws Exception {
        blackhole.consume(JavaDoubleParser.parseDouble(test1000000));
    }
}
