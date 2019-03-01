package com.helpcrunch.helpcrunchdemo.screens;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.helpcrunch.helpcrunchdemo.R;
import com.helpcrunch.library.core.Callback;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.HelpCrunchOptions;
import com.helpcrunch.library.ui.HelpCrunchChatExtraKeys;
import com.helpcrunch.library.ui.design.HelpCrunchButton;
import com.helpcrunch.library.ui.design.HelpCrunchDesign;
import com.helpcrunch.library.ui.design.MessageArea;
import com.helpcrunch.library.utils.HCViewUtils;

import static com.helpcrunch.library.core.HelpCrunch.FCM_INTENT_ACTION_ID;

public class MainActivity extends AppCompatActivity {

    private View badge1View;
    private View badge2View;
    private View badge3View;
    private View badge4View;

    private TextView badge1TextView;
    private TextView badge2TextView;
    private TextView badge3TextView;
    private TextView badge4TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

        initViews();

        (findViewById(R.id.chatButton)).setOnClickListener(view -> HelpCrunch.showChatScreen(MainActivity.this));

        (findViewById(R.id.anonimousChatButton)).setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putBoolean(HelpCrunchChatExtraKeys.REQUEST_NAME, false);
            HelpCrunch.showChatScreen(MainActivity.this, bundle);
        });

        (findViewById(R.id.chatActivityButton)).setOnClickListener(view -> {
            //Optional.
            //  Set the necessary options to change the style.
            HelpCrunchDesign design = new HelpCrunchDesign()
                    .setToolbarTitle("SUPPORT");


            HelpCrunchOptions options = new HelpCrunchOptions()
                    .setDesign(design)
                    .setRequestName(false);

            Intent intent = new Intent(MainActivity.this, ChatInheritedActivity.class);

            //  Optional. You cau also use showChatScreen(Context, HelpCrunchOptions)
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

        (findViewById(R.id.customDesignButton)).setOnClickListener(view -> {
            //Optional.
            //  Set the necessary options to change the style.
            MessageArea messageArea = new MessageArea()
                    .setAttachmentPopupTitle("Custom title")
                    .setAttachmentPopupCameraText("Custom camera")
                    .setAttachmentPopupGalleryText("Custom gallery")
                    .setAttachmentsButtonVisible(true);

            HelpCrunchButton backButton = new HelpCrunchButton();
            backButton.setImageSrcRes(R.drawable.ic_arrow_back);

            HelpCrunchButton attachmentsButton = new HelpCrunchButton();
            attachmentsButton.setImageSrcRes(R.drawable.ic_attach_file);

            HelpCrunchButton sendButton = new HelpCrunchButton();
            sendButton.setImageSrcRes(R.drawable.ic_send);
            sendButton.setBackgroundRes(R.drawable.bg_oval_red);

            HelpCrunchDesign design = new HelpCrunchDesign()
                    .setToolbarImage(R.drawable.ic_hc_image)
                    .setToolbarVisible(true)
                    .setCustomerAvatarVisible(false)
                    .setActionBackButton(backButton)
                    .setActionAttachmentButton(attachmentsButton)
                    .setActionSendButton(sendButton)
                    .setChatBackgroundRes(R.drawable.bg_chat)
                    .setIncomingBubbleColor(R.color.colorIn)
                    .setOutcomingBubbleColor(R.color.colorOut)
                    .setMessageArea(messageArea);


            HelpCrunchOptions options = new HelpCrunchOptions()
                    .setDesign(design)
                    .setRequestName(true);

            HelpCrunch.showChatScreen(MainActivity.this, options);
        });

        registerReceiver(fcmBroadcastReceiver, new IntentFilter(FCM_INTENT_ACTION_ID));
    }

    private void initViews() {
        badge1View = findViewById(R.id.badge1View);
        badge2View = findViewById(R.id.badge2View);
        badge3View = findViewById(R.id.badge3View);
        badge4View = findViewById(R.id.badge4View);

        badge1TextView = findViewById(R.id.badge1TextView);
        badge2TextView = findViewById(R.id.badge2TextView);
        badge3TextView = findViewById(R.id.badge3TextView);
        badge4TextView = findViewById(R.id.badge4TextView);
    }


    private void updateUnreadMessages() {
        HelpCrunch.getUnreadMessagesCount(MainActivity.this, new Callback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                setVisibilityForUnreadMessagesBadge(result);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void setVisibilityForUnreadMessagesBadge(int count) {
        int visibility = count > 0 ? View.VISIBLE : View.INVISIBLE;

        badge1View.setVisibility(visibility);
        badge2View.setVisibility(visibility);
        badge3View.setVisibility(visibility);
        badge4View.setVisibility(visibility);

        String countString = String.valueOf(count);

        badge1TextView.setText(countString);
        badge2TextView.setText(countString);
        badge3TextView.setText(countString);
        badge4TextView.setText(countString);
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
            updateUnreadMessages();
        }
    };
}
