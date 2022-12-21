package org.example.jackson.bench;

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

    public static long alternateParseLong(String s) {
        /* Ok: let's keep strategy simple: ignoring optional minus sign,
         * we'll accept 1 - 19 digits and parse things efficiently;
         * otherwise just defer to JDK parse functionality.
         */
        char c = s.charAt(0);
        int len = s.length();
        boolean neg = (c == '-');
        int offset = 1;
        // must have 1 - 19 digits after optional sign:
        // negative?
        if (neg) {
            if (len == 1 || len > 20) {
                return Long.parseLong(s);
            }
            c = s.charAt(offset++);
        } else {
            if (len > 19) {
                return Long.parseLong(s);
            }
        }
        if (c > '9' || c < '0') {
            return Integer.parseInt(s);
        }
        long num = c - '0';
        if (offset < len) {
            c = s.charAt(offset++);
            if (c > '9' || c < '0') {
                return Long.parseLong(s);
            }
            num = (num * 10) + (c - '0');
            if (offset < len) {
                c = s.charAt(offset++);
                if (c > '9' || c < '0') {
                    return Long.parseLong(s);
                }
                num = (num * 10) + (c - '0');
                // Let's just loop if we have more than 3 digits:
                if (offset < len) {
                    do {
                        c = s.charAt(offset++);
                        if (c > '9' || c < '0') {
                            return Long.parseLong(s);
                        }
                        num = (num * 10) + (c - '0');
                    } while (offset < len);
                }
            }
        }
        return neg ? -num : num;
    }

}
