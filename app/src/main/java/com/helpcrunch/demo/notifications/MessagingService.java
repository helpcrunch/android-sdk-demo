package com.helpcrunch.demo.notifications;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.HelpCrunchExt;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * An example of resolving conflicts when using multiple services using the FCM
 */
public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull @NotNull String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (HelpCrunchExt.isHelpCrunchMessage(remoteMessage)) {
            HelpCrunch.showNotification(remoteMessage);
        } else {
            //Do something yours
        }
    }
}
