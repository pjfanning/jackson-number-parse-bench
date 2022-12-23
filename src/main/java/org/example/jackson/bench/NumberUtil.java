package org.example.jackson.bench;

import java.util.Iterator;

import com.fasterxml.jackson.core.io.NumberInput;

public class NumberUtil {
    public static long jacksonParseLong(String s) {
        // Ok, now; as the very first thing, let's just optimize case of "fake longs";
        // that is, if we know they must be ints, call int parsing
        int length = s.length();
        if (length <= 9) {
            return (long) NumberInput.parseInt(s);
        }
        // !!! TODO: implement efficient 2-int parsing...
        return Long.parseLong(s);
    }

    public static long alternateParseSimplified(String s) {
        /* Ok: let's keep strategy simple: ignoring optional minus sign,
         * we'll accept 1 - 19 digits and parse things efficiently;
         * otherwise just defer to JDK parse functionality.
         */
        int len = s.length();
        boolean neg = s.charAt(0) == '-';
        int offset;
        // must have 1 - 19 digits after optional sign:
        // negative?
        if (neg) {
            if (len == 1 || len > 20) {
                return Long.parseLong(s);
            }
            offset = 1;
        } else {
            if (len > 19) {
                return Long.parseLong(s);
            }
            offset = 0;
        }
        long num = 0L;
        for (int i = offset; i < len; i++) {
            char c = s.charAt(i);
            if (c > '9' || c < '0') {
                return Long.parseLong(s);
            }
            num = (num * 10) + (c - '0');
            if (i == len - 1 && num < 0) {
                // overflow
                throw new NumberFormatException("For input string: " + s);
            }
        }
        return neg ? -num : num;
    }
    
    public static long alternateParseSwitch(String s) {
        /* Ok: let's keep strategy simple: ignoring optional minus sign,
         * we'll accept 1 - 19 digits and parse things efficiently;
         * otherwise just defer to JDK parse functionality.
         */
        int len = s.length();
        boolean neg = s.charAt(0) == '-';
        int off;
        // must have 1 - 19 digits after optional sign:
        // negative?
        if (neg) {
            if (len == 1 || len > 20) {
                return Long.parseLong(s);
            }
            off = 1;
        } else {
            if (len > 19) {
                return Long.parseLong(s);
            }
            off = 0;
        }
        long num = s.charAt(len - 1) - '0';
        
        switch(len- off) {
            case 19: 
                num += (digitAt(s, len - 19)) * 1000000000000000000L;
            case 18: 
                num += (digitAt(s, len - 18)) * 100000000000000000L;
            case 17: 
                num += (digitAt(s, len - 17)) * 10000000000000000L;
            case 16: 
                num += (digitAt(s, len - 16)) * 1000000000000000L;
            case 15: 
                num += (digitAt(s, len - 15)) * 100000000000000L;
            case 14: 
                num += (digitAt(s, len - 14)) * 10000000000000L;
            case 13: 
                num += (digitAt(s, len - 13)) * 1000000000000L;
            case 12: 
                num += (digitAt(s, len - 12)) * 100000000000L;
            case 11: 
                num += (digitAt(s, len - 11)) * 10000000000L;
            case 10: 
                num += (digitAt(s, len - 10)) * 1000000000L;
            case 9: 
              num += (digitAt(s, len - 9)) * 100000000L;
            case 8: 
              num += (digitAt(s, len - 8)) * 10000000L;
            case 7: 
              num += (digitAt(s, len - 7)) * 1000000L;
            case 6: 
              num += (digitAt(s, len - 6)) * 100000L;
            case 5: 
              num += (digitAt(s, len - 5)) * 10000L;
            case 4: 
              num += (digitAt(s, len - 4)) * 1000L;
            case 3: 
              num += (digitAt(s, len - 3)) * 100L;
            case 2: 
              num += (digitAt(s, len - 2)) * 10L;
        }
        return neg ? -num : num;
    }
    
    private static long digitAt(String s, int i) {
        char c = s.charAt(i);
        if (c > '9' || c < '0') {
            return Long.parseLong(s);
        }
        return c - '0';
    }

}
