package sk.seky.android.gcm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.*;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by lsekerak on 3. 4. 2016.
 */
public class GcmClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(GcmClient.class.getName());

    private final HttpHeaders headers;
    private final HttpRequestFactory httpRequestFactory;
    private final JsonFactory jsonFactory;
    private final ObjectMapper objectMapper;

    public GcmClient(HttpRequestFactory httpRequestFactory, JsonFactory jsonFactory, String key, ObjectMapper objectMapper) {
        this.httpRequestFactory = httpRequestFactory;
        this.jsonFactory = jsonFactory;
        this.objectMapper = objectMapper;
        enableLogging();

        headers = new HttpHeaders();
        headers.setContentType("application/json");
        headers.setAuthorization("key=" + key);
    }

    public static void enableLogging() {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HttpTransport.class.getName());
        logger.setLevel(Level.INFO);
        logger.addHandler(new Handler() {

            @Override
            public void close() throws SecurityException {
            }

            @Override
            public void flush() {
            }

            @Override
            public void publish(LogRecord record) {
                LOGGER.info(record.getMessage());
            }
        });
    }

    public String send(Message message) throws IOException {
        GenericUrl gurl = new GenericUrl("https://android.googleapis.com/gcm/send");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        objectMapper.writeValue(out, message);
        HttpContent content = new ByteArrayContent("application/javascript", out.toByteArray());
        String value = new String(out.toByteArray(), "UTF-8");
        //LOGGER.debug(value);

        HttpRequest request = httpRequestFactory.buildPostRequest(gurl, content);
        request.setHeaders(headers);
        request.setParser(new JsonObjectParser(jsonFactory));
        request.setContent(content);
        String response = request.execute().parseAsString();
        LOGGER.debug(response);
        return response;
    }
}
