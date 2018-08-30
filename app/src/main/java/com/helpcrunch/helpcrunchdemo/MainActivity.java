package com.helpcrunch.helpcrunchdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.helpcrunch.library.core.Callback;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.HelpCrunchOptions;
import com.helpcrunch.library.model.MessagesCountResponse;
import com.helpcrunch.library.ui.HelpCrunchChatExtraKeys;
import com.helpcrunch.library.ui.design.HelpCrunchDesign;
import com.helpcrunch.library.ui.design.MessageArea;
import com.helpcrunch.library.utils.HCViewUtils;

import static com.helpcrunch.library.core.HelpCrunch.FCM_INTENT_ACTION_ID;

public class MainActivity extends AppCompatActivity {

    Button changeUserDataButton;
    Button sendCustomDataButton;

    View badge1ImageView;
    View badge2ImageView;
    View badge3ImageView;
    View badge4ImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);


        (findViewById(R.id.chatButton)).setOnClickListener(view -> HelpCrunch.showChatScreen(MainActivity.this));

        (findViewById(R.id.anonimousChatButton)).setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putBoolean(HelpCrunchChatExtraKeys.REQUEST_NAME, false);
            HelpCrunch.showChatScreen(MainActivity.this, bundle);
        });

        (findViewById(R.id.chatActivityButton)).setOnClickListener(view -> {
            //Optional.
            //  Set the necessary options to change the style.
            MessageArea messageArea = new MessageArea()
                    .setAttachmentPopupTitle("Custom title")
                    .setAttachmentPopupCameraText("Custom camera")
                    .setAttachmentPopupGalleryText("Custom gallery")
                    .setAttachmentsButtonVisible(true);

            HelpCrunchDesign design = new HelpCrunchDesign()
                    .setToolbarTitle("THIS IS MY ACTIVITY")
                    .setMessageArea(messageArea);


            HelpCrunchOptions options = new HelpCrunchOptions()
                    .setDesign(design)
                    .setRequestName(true);

            Intent intent = new Intent(MainActivity.this, ChatActivityInherited.class);
            //  Optional.
            intent.putExtras(options.toBundle());
            startActivity(intent);

        });

        (findViewById(R.id.chatFragmentButton)).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, PlainChatActivity.class)));

        findViewById(R.id.changeUserDataButton).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, UserDataActivity.class)));

        findViewById(R.id.sendCustomDataButton).setOnClickListener(view -> startActivity(new Intent(MainActivity.this, CustomUserDataActivity.class)));

        findViewById(R.id.logoutButton).setOnClickListener(view -> HelpCrunch.logout(MainActivity.this, new Callback<Void>() {
            @Override
            public void onSuccess(Void result) {
                HCViewUtils.toast(MainActivity.this, "Success");
            }

            @Override
            public void onError(Exception e) {

            }
        }));

        badge1ImageView = findViewById(R.id.badge1ImageView);
        badge2ImageView = findViewById(R.id.badge2ImageView);
        badge3ImageView = findViewById(R.id.badge3ImageView);
        badge4ImageView = findViewById(R.id.badge4ImageView);

        updateUnreadMessages();

        registerReceiver(fcmBroadcastReceiver, new IntentFilter(FCM_INTENT_ACTION_ID));
    }


    private void updateUnreadMessages() {
        HelpCrunch.getUnreadMessagesCount(MainActivity.this, new Callback<MessagesCountResponse>() {
            @Override
            public void onSuccess(MessagesCountResponse result) {
                setVisibilityForUnreadMessagesBadge(result.getMessagesCount() > 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }


    private void setVisibilityForUnreadMessagesBadge(int visibility) {
        badge1ImageView.setVisibility(visibility);
        badge2ImageView.setVisibility(visibility);
        badge3ImageView.setVisibility(visibility);
        badge4ImageView.setVisibility(visibility);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(fcmBroadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUnreadMessages();
    }


    private BroadcastReceiver fcmBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            setVisibilityForUnreadMessagesBadge(View.VISIBLE);
            updateUnreadMessages();
        }
    };
}
