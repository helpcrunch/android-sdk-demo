package com.helpcrunch.demo.notifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.helpcrunch.library.core.HelpCrunch.showNotification
import com.helpcrunch.library.core.isHelpCrunchMessage

/**
 * An example of resolving conflicts when using multiple services using the FCM
 */
class MessagingService : FirebaseMessagingService() {
    override fun onNewToken(s: String) {
        super.onNewToken(s)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.isHelpCrunchMessage()) {
            showNotification(remoteMessage)
        } else {
            //Do something yours
            Log.i("MessagingService", "I can handle a message here")
        }
    }
}