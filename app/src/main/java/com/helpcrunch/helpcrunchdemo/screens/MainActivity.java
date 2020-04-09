package com.helpcrunch.helpcrunchdemo.screens;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.helpcrunch.helpcrunchdemo.R;
import com.helpcrunch.helpcrunchdemo.design.CustomTheme;
import com.helpcrunch.library.core.Callback;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.options.*;
import com.helpcrunch.library.core.options.design.*;
import com.helpcrunch.library.core.options.files.FileExtension;

import org.jetbrains.annotations.NotNull;

import static com.helpcrunch.helpcrunchdemo.application.App.APP_ID;
import static com.helpcrunch.helpcrunchdemo.application.App.ORGANIZATION;
import static com.helpcrunch.helpcrunchdemo.application.App.SECRET;

public class MainActivity extends AppCompatActivity {

    private View badge1View;

    private TextView badge1TextView;

    private BroadcastReceiver hcEventsBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            HelpCrunch.Event parcelableExtra = (HelpCrunch.Event) intent.getSerializableExtra(HelpCrunch.EVENT_TYPE);
            HelpCrunch.Screen screen = (HelpCrunch.Screen) intent.getSerializableExtra(HelpCrunch.SCREEN_TYPE);

            if (parcelableExtra == null) {
                Log.w(HelpCrunch.EVENTS, "Can't receive data");
                return;
            }

            switch (parcelableExtra) {
                case FIRST_MESSAGE:
                    Log.i(HelpCrunch.EVENTS, "First Message");
                    break;
                case SCREEN_CLOSED:
                    if (screen != null) {
                        Log.i(HelpCrunch.EVENTS, screen.toString() + " screen: closed");
                    } else {
                        Log.w(HelpCrunch.EVENTS, "Can't receive screen event");
                    }
                    break;
                case SCREEN_OPENED:
                    if (screen != null) {
                        Log.i(HelpCrunch.EVENTS, screen.toString() + " screen: opened");
                    } else {
                        Log.w(HelpCrunch.EVENTS, "Can't receive screen event");
                    }
                    break;

                case ON_IMAGE_URL:
                case ON_FILE_URL:
                case ON_ANY_OTHER_URL:
                    String url = intent.getStringExtra(HelpCrunch.URL);
                    Log.i(HelpCrunch.EVENTS, parcelableExtra.toString() + ": " + url);
                    break;

                case ON_UNREAD_COUNT_CHANGED:
                    Log.i(HelpCrunch.EVENTS, "new unread message");
                    updateUnreadMessages();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

        initViews();

        findViewById(R.id.chatButton).setOnClickListener(v -> {
            checkSettingsOpenScreen();
            clearBadge();
            HelpCrunch.trackEvent("Event chat opened", "https://i.pinimg.com/originals/58/92/e7/5892e7f3cc64c8a912e2494a3ff77e08.jpg", "Say Cheese");
        });

        findViewById(R.id.chatCustomButton).setOnClickListener(v -> openWithCustomSettings());
        findViewById(R.id.customUserDataButton).setOnClickListener(v -> openCustomUserDataScreen());
        findViewById(R.id.userDataButton).setOnClickListener(v -> openUserDataScreen());

        findViewById(R.id.userData).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, UserDataActivity.class)));

        findViewById(R.id.logoutButton).setOnClickListener(v -> logout());


        String version = "SDK: v" + HelpCrunch.getVersion();

        ((TextView) findViewById(R.id.version)).setText(version);

        registerReceiver(hcEventsBroadcastReceiver, new IntentFilter(HelpCrunch.EVENTS));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUnreadMessages();
        findViewById(R.id.logoutButton).setEnabled(HelpCrunch.getUser() != null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(hcEventsBroadcastReceiver);
    }

    private void openCustomUserDataScreen() {
        startActivity(new Intent(MainActivity.this, CustomUserDataActivity.class));
    }

    private void openUserDataScreen() {
        startActivity(new Intent(MainActivity.this, UserDataActivity.class));
    }

    private void logout() {
        findViewById(R.id.progress).setVisibility(View.VISIBLE);
        findViewById(R.id.logoutButton).setEnabled(false);

        HelpCrunch.logout(new Callback<Object>() {
            @Override
            public void onSuccess(Object result) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                clearBadge();
                findViewById(R.id.progress).setVisibility(View.GONE);
                findViewById(R.id.logoutButton).setEnabled(false);
            }

            @Override
            public void onError(@NotNull String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                findViewById(R.id.progress).setVisibility(View.GONE);
                findViewById(R.id.logoutButton).setEnabled(true);
            }
        });
    }

    private void clearBadge() {
        badge1TextView.setText(null);
        badge1View.setVisibility(View.INVISIBLE);
    }

    private void checkSettingsOpenScreen() {
        HCTheme theme = new HCTheme.Builder(HCTheme.DEFAULT)
                .build();

        switch (((RadioGroup) findViewById(R.id.theme_group)).getCheckedRadioButtonId()) {
            case R.id.light:
                theme = new HCTheme.Builder(HCTheme.DEFAULT).build();
                break;
            case R.id.dark:
                theme = new HCTheme.Builder(HCTheme.DARK).build();
                break;
            case R.id.custom_color:
                HCMessageAreaTheme messageAreaTheme = new HCMessageAreaTheme.Builder()
                        .setButtonType(HCMessageAreaTheme.ButtonType.TEXT)
                        .build();

                theme = new HCTheme.Builder(R.color.main_color, true)
                        .setMessageAreaTheme(messageAreaTheme)
                        .build();
                break;
            case R.id.custom:
                theme = CustomTheme.getDesign();
                break;
        }

        HCOptions.Builder optionsBuilder = new HCOptions.Builder().setTheme(theme);

        if (((Switch) findViewById(R.id.pre_chat_switch)).isChecked()) {
            HCPreChatForm preChatForm = new HCPreChatForm.Builder()
                    .withName(true, null)
                    .withEmail(true)
                    .withPhone(false, null)
                    .build();

            optionsBuilder.setPreChatForm(preChatForm);
        }

        HelpCrunch.showChatScreen(MainActivity.this, optionsBuilder.build());
    }

    private void openWithCustomSettings() {
        HCMessageAreaTheme messageAreaTheme = new HCMessageAreaTheme.Builder()
                .setButtonType(HCMessageAreaTheme.ButtonType.TEXT)
                .build();

        HCTheme theme = new HCTheme.Builder(R.color.send_bg_enable_color, true)
                .setMessageAreaTheme(messageAreaTheme)
                .build();

        HCPreChatForm preChatForm = new HCPreChatForm.Builder()
                .withName(true)
                .withCompany(true)
                .withEmail(true)
                .withPhone(false)
                .withField("customAttribute", "My custom", true)
                .withField("customAttribute1", "Please enter something", true)
                .build();

        HCOptions options = new HCOptions.Builder()
                .setTheme(theme)
                .setPreChatForm(preChatForm)
                .setFileExtensions(new FileExtension[]{
                        new FileExtension("PDF", "pdf"),
                        new FileExtension("IMAGES", new String[]{"jpg", "png"})
                })
                .build();

        HelpCrunch.showChatScreen(MainActivity.this, options);
    }

    private void initViews() {
        badge1View = findViewById(R.id.badge1View);

        badge1TextView = findViewById(R.id.badge1TextView);
    }

    private void updateUnreadMessages() {
        HelpCrunch.getUnreadMessagesCount(new Callback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                setVisibilityForUnreadMessagesBadge(result);
            }

            @Override
            public void onError(@NotNull String message) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setVisibilityForUnreadMessagesBadge(int count) {
        badge1View.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
        String countString = String.valueOf(count);

        badge1TextView.setText(countString);
    }

}