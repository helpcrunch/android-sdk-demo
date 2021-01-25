package com.helpcrunch.helpcrunchdemo.notifications;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.helpcrunch.library.core.HelpCrunch;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * An example of resolving conflicts when using multiple services using the FCM
 */
class MessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull @NotNull String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (isHelpCrunchMessage(remoteMessage)) {
            HelpCrunch.showNotification(remoteMessage);
        } else {
            //Do something yours
        }
    }

    /**
     * Checks that the message came from the HelpCrunch
     */
    private boolean isHelpCrunchMessage(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();

        return data.get("type") != null
                && data.get("app") != null
                && data.get("type") != null
                && data.get("sender") != null
                && data.get("organization") != null;
    }
}
