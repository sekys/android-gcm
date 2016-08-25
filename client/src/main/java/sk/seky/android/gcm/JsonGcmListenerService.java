package sk.seky.android.gcm;

import android.os.Bundle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.gcm.GcmListenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JsonGcmListenerService extends GcmListenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationTokenService.class);

    private final ObjectMapper mapper;

    public JsonGcmListenerService() {
        mapper = new ObjectMapper();
    }

    /**
     * Called when message is received.
     *
     * @param from   SenderID of the sender.
     * @param bundle Data bundle containing message data as key/value pairs.
     *               For Set of keys use data.keySet().
     */
    @Override
    public void onMessageReceived(String from, Bundle bundle) {
        LOGGER.debug("From: {}\n Data: {}", from, bundle);
        try {
            String className = bundle.getString("type");
            Class classType = this.getClassLoader().loadClass(className);
            String json = bundle.getString("data");
            Object data = mapper.readValue(json, classType);
            consumeMessage(from, data);
        } catch (Exception e) {
            LOGGER.error("fatal error", e);
        }
    }

    protected abstract void consumeMessage(String from, Object data);
}
