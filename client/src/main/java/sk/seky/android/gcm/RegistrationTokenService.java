package sk.seky.android.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationTokenService extends IntentService {
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationTokenService.class);
    private final String gcmSenderId;

    public RegistrationTokenService(String gcmSenderId) {
        super(RegistrationTokenService.class.getSimpleName());
        this.gcmSenderId = gcmSenderId;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = null;
        try {
            // [START register_for_gcm]
            // Initially this call goes out to the network to retrieve the token, subsequent calls
            // are local.
            // R.string.gcm_defaultSenderId (the Sender ID) is typically derived from google-services.json.
            // See https://developers.google.com/cloud-messaging/android/start for details on this file.
            InstanceID instanceID = InstanceID.getInstance(this);
            token = instanceID.getToken(gcmSenderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            LOGGER.info("GCM Registration Token: {}", token);
        } catch (Exception e) {
            LOGGER.error("Failed to complete token refresh", e);
        }
        sharedPreferences.edit().putString(GcmService.GCM_TOKEN, token).apply();
        Intent registrationComplete = new Intent(REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}
