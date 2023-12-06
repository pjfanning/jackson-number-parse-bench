package org.example.jackson.bench;

import ch.randelshofer.fastdoubleparser.JavaBigDecimalParser;
import com.fasterxml.jackson.core.io.BigDecimalParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

import java.math.BigDecimal;

public class BigDecimalParser2Bench extends BenchmarkLauncher {
    static final String BIG_NUM = genNum();
    
    private static String genNum() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2000; i++) {
            sb.append("1");
        }
        return sb.toString();
    }
    
    @Benchmark
    public void currentParser(Blackhole blackhole) throws Exception {
        blackhole.consume(BigDecimalParser.parse(BIG_NUM));
    }

    @Benchmark
    public void javaParser(Blackhole blackhole) throws Exception {
        blackhole.consume(new BigDecimal(BIG_NUM));
    }

    @Benchmark
    public void fastParser(Blackhole blackhole) throws Exception {
        blackhole.consume(FastBigDecimalParser.parse(BIG_NUM));
    }

    @Benchmark
    public void randelshoferFastParser(Blackhole blackhole) throws Exception {
        blackhole.consume(JavaBigDecimalParser.parseBigDecimal(BIG_NUM));
    }
}
