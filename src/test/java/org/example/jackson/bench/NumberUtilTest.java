package org.example.jackson.bench;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class NumberUtilTest {

    @Test
    void testLongOverflow() {
        try {
            // Long.MAX_VALUE + 1
            NumberUtil.alternateParseSimplified("9223372036854775808");
            fail("expected NumberFormatException");
        } catch (NumberFormatException nfe) {
            //
        }
        try {
            // Long.MIN_VALUE - 1
            NumberUtil.alternateParseSimplified("-9223372036854775809");
            fail("expected NumberFormatException");
        } catch (NumberFormatException nfe) {
            //
        }
    }
}
