package org.example.jackson.bench;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.async.ByteArrayFeeder;
import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class AsyncParserTest {

    private static byte[] ARRAY;
    private static final JsonFactory FACTORY = new JsonFactory();

    static {
        try (InputStream stream = AsyncParserTest.class.getResourceAsStream("/float-array.txt")) {
            ARRAY = IOUtils.toByteArray(stream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void bigArrayOfFloatPrimitives() throws Exception {
        NonBlockingJsonParser parser = (NonBlockingJsonParser) FACTORY.createNonBlockingByteArrayParser();
        ByteArrayFeeder feeder = parser.getNonBlockingInputFeeder();
        feeder.feedInput(ARRAY, 0, ARRAY.length);
        JsonToken token = null;
        while (token != JsonToken.NOT_AVAILABLE) {
            token = parser.nextToken();
        }
    }
}
