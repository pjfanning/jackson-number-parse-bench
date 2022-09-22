package org.example.jackson.bench;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FastBigDecimalParserTest {

    @Test
    public void testParse() {
        testParse("123");
        testParse("-123");
        testParse("123.456");
        testParse("-123.456");
        testParse("12345678900987654321");
        testParse("-12345678900987654321");
        testParse("1234567890.0987654321");
        testParse("-1234567890.0987654321");
    }

    @Test
    public void testParseExp() {
        testParse("1.23456789E-3");
        testParse("1234567890.0987654321E-127");
        testParse("1E+3");
        testParse("1E-3");
        testParse("-1E+3");
        testParse("-1E-3");
    }

    private void testParse(String s) {
        assertEquals(new BigDecimal(s), FastBigDecimalParser.parse(s));
    }
}
