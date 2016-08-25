package sk.seky.android.gcm;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.android.gms.gcm.GcmPubSub;

import java.io.IOException;
import java.util.Set;

/**
 * Created by lsekerak on 9. 5. 2016.
 */
public final class GcmService {
    public static final String GCM_TOKEN = "token";

    private final Context context;

    public GcmService(Context context) {
        this.context = context;
    }

    public String getToken() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(GCM_TOKEN, null);
    }

    public void subscribeTopics(Set<String> oldTopics, Set<String> newTopics) throws IOException {
        String token = getToken();
        GcmPubSub pubSub = GcmPubSub.getInstance(context);
        for (String topic : oldTopics) {
            // "/topics/[a-zA-Z0-9-_.~%]+"
            if (!newTopics.contains(topic)) {
                pubSub.unsubscribe(token, "/topics/" + topic);
            }

        }
        for (String topic : newTopics) {
            if (!oldTopics.contains(topic)) {
                pubSub.subscribe(token, "/topics/" + topic, null);
            }
        }
    }
}
