package sk.seky.android.gcm;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import com.google.api.client.util.Preconditions;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by lsekerak on 3. 4. 2016.
 */
public class JacksonFactory extends JsonFactory {

    private final com.fasterxml.jackson.core.JsonFactory factory;

    public JacksonFactory(com.fasterxml.jackson.core.JsonFactory factory) {
        this.factory = factory;
    }

    public static JsonToken convert(com.fasterxml.jackson.core.JsonToken token) {
        if (token == null) {
            return null;
        }
        switch (token) {
            case END_ARRAY:
                return JsonToken.END_ARRAY;
            case START_ARRAY:
                return JsonToken.START_ARRAY;
            case END_OBJECT:
                return JsonToken.END_OBJECT;
            case START_OBJECT:
                return JsonToken.START_OBJECT;
            case VALUE_FALSE:
                return JsonToken.VALUE_FALSE;
            case VALUE_TRUE:
                return JsonToken.VALUE_TRUE;
            case VALUE_NULL:
                return JsonToken.VALUE_NULL;
            case VALUE_STRING:
                return JsonToken.VALUE_STRING;
            case VALUE_NUMBER_FLOAT:
                return JsonToken.VALUE_NUMBER_FLOAT;
            case VALUE_NUMBER_INT:
                return JsonToken.VALUE_NUMBER_INT;
            case FIELD_NAME:
                return JsonToken.FIELD_NAME;
            default:
                return JsonToken.NOT_AVAILABLE;
        }
    }

    @Override
    public JsonGenerator createJsonGenerator(OutputStream out, Charset enc) throws IOException {
        return new JacksonGenerator(this, factory.createGenerator(out, com.fasterxml.jackson.core.JsonEncoding.UTF8));
    }

    @Override
    public JsonGenerator createJsonGenerator(Writer writer) throws IOException {
        return new JacksonGenerator(this, factory.createGenerator(writer));
    }

    @Override
    public JsonParser createJsonParser(Reader reader) throws IOException {
        Preconditions.checkNotNull(reader);
        return new JacksonParser(this, factory.createParser(reader));
    }

    @Override
    public JsonParser createJsonParser(InputStream in) throws IOException {
        Preconditions.checkNotNull(in);
        return new JacksonParser(this, factory.createParser(in));
    }

    @Override
    public JsonParser createJsonParser(InputStream in, Charset charset) throws IOException {
        Preconditions.checkNotNull(in);
        return new JacksonParser(this, factory.createParser(in));
    }

    @Override
    public JsonParser createJsonParser(String value) throws IOException {
        Preconditions.checkNotNull(value);
        return new JacksonParser(this, factory.createParser(value));
    }
}
