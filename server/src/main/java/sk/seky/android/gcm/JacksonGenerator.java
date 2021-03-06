package sk.seky.android.gcm;

import com.google.api.client.json.JsonGenerator;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by lsekerak on 3. 4. 2016.
 */
public class JacksonGenerator extends JsonGenerator {
    private final com.fasterxml.jackson.core.JsonGenerator generator;
    private final JacksonFactory factory;

    JacksonGenerator(JacksonFactory factory, com.fasterxml.jackson.core.JsonGenerator generator) {
        this.factory = factory;
        this.generator = generator;
    }

    @Override
    public JacksonFactory getFactory() {
        return factory;
    }

    @Override
    public void flush() throws IOException {
        generator.flush();
    }

    @Override
    public void close() throws IOException {
        generator.close();
    }

    @Override
    public void writeBoolean(boolean state) throws IOException {
        generator.writeBoolean(state);
    }

    @Override
    public void writeEndArray() throws IOException {
        generator.writeEndArray();
    }

    @Override
    public void writeEndObject() throws IOException {
        generator.writeEndObject();
    }

    @Override
    public void writeFieldName(String name) throws IOException {
        generator.writeFieldName(name);
    }

    @Override
    public void writeNull() throws IOException {
        generator.writeNull();
    }

    @Override
    public void writeNumber(int v) throws IOException {
        generator.writeNumber(v);
    }

    @Override
    public void writeNumber(long v) throws IOException {
        generator.writeNumber(v);
    }

    @Override
    public void writeNumber(BigInteger v) throws IOException {
        generator.writeNumber(v);
    }

    @Override
    public void writeNumber(double v) throws IOException {
        generator.writeNumber(v);
    }

    @Override
    public void writeNumber(float v) throws IOException {
        generator.writeNumber(v);
    }

    @Override
    public void writeNumber(BigDecimal v) throws IOException {
        generator.writeNumber(v);
    }

    @Override
    public void writeNumber(String encodedValue) throws IOException {
        generator.writeNumber(encodedValue);
    }

    @Override
    public void writeStartArray() throws IOException {
        generator.writeStartArray();
    }

    @Override
    public void writeStartObject() throws IOException {
        generator.writeStartObject();
    }

    @Override
    public void writeString(String value) throws IOException {
        generator.writeString(value);
    }

    @Override
    public void enablePrettyPrint() throws IOException {
        generator.useDefaultPrettyPrinter();
    }
}
