package org.example.jackson.bench;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;

public class SchubfachBench extends BenchmarkLauncher {

    private static final int SIZE = 10;
    private static final float[] FLOATS = new float[SIZE];
    private static final double[] DOUBLES = new double[SIZE];

    static {
        final Random rnd = new Random();
        for (int i = 0; i < SIZE; i++) {
            FLOATS[i] = rnd.nextFloat();
            DOUBLES[i] = rnd.nextDouble();
        }
    }

    @Benchmark
    public void doubleWithoutThreadLocal(Blackhole blackhole) throws Exception {
        for (int i = 0; i < SIZE; i++) {
            blackhole.consume(org.example.jackson.bench.schubfach.DoubleToDecimal.toString(DOUBLES[i]));
        }
    }

    @Benchmark
    public void doubleWithThreadLocal(Blackhole blackhole) throws Exception {
        for (int i = 0; i < SIZE; i++) {
            blackhole.consume(com.fasterxml.jackson.core.io.schubfach.DoubleToDecimal.toString(DOUBLES[i]));
        }
    }

    @Benchmark
    public void floatWithoutThreadLocal(Blackhole blackhole) throws Exception {
        for (int i = 0; i < SIZE; i++) {
            blackhole.consume(org.example.jackson.bench.schubfach.FloatToDecimal.toString(FLOATS[i]));
        }
    }

    @Benchmark
    public void floatWithThreadLocal(Blackhole blackhole) throws Exception {
        for (int i = 0; i < SIZE; i++) {
            blackhole.consume(com.fasterxml.jackson.core.io.schubfach.FloatToDecimal.toString(FLOATS[i]));
        }
    }
}
