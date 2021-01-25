package com.helpcrunch.helpcrunchdemo.screens;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.helpcrunch.helpcrunchdemo.R;
import com.helpcrunch.library.core.Callback;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.models.user.HCUser;
import com.helpcrunch.library.core.options.HCOptions;
import com.helpcrunch.library.core.options.HCPreChatForm;
import com.helpcrunch.library.core.options.design.HCMessageAreaTheme;
import com.helpcrunch.library.core.options.design.HCTheme;
import com.helpcrunch.library.core.options.files.FileExtension;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private final BroadcastReceiver hcStateBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            HelpCrunch.State state = (HelpCrunch.State) intent.getSerializableExtra(HelpCrunch.STATE_TYPE);

            ((TextView) findViewById(R.id.state)).setText(getStateString(state == null ? HelpCrunch.State.IDLE : state));
        }
    };
    private View badge1View;
    private TextView badge1TextView;
    private final BroadcastReceiver hcEventsBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            HelpCrunch.Event event = (HelpCrunch.Event) intent.getSerializableExtra(HelpCrunch.EVENT_TYPE);
            HelpCrunch.Screen screen = (HelpCrunch.Screen) intent.getSerializableExtra(HelpCrunch.SCREEN_TYPE);
            HashMap<String, String> data = (HashMap<String, String>) intent.getSerializableExtra(HelpCrunch.EVENT_DATA);

            if (event == null) {
                Log.w(HelpCrunch.EVENTS, "Can't receive data");
                return;
            }

            switch (event) {
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
                        Log.i(HelpCrunch.EVENTS, screen.toString() + " screen: opened, data: " + (data == null ? "null" : data.toString()));
                    } else {
                        Log.w(HelpCrunch.EVENTS, "Can't receive screen event");
                    }
                    break;

                case ON_IMAGE_URL:
                case ON_FILE_URL:
                case ON_ANY_OTHER_URL:
                    Log.i(HelpCrunch.EVENTS, "Url opened. data: " + (data == null ? "null" : data.toString()));

                    break;

                case ON_UNREAD_COUNT_CHANGED:
                    Log.i(HelpCrunch.EVENTS, "new unread message");
                    updateUnreadMessages();
                    break;
            }
        }
    };
    private RadioGroup themeRadioGroup;
    private CheckBox defaultOptionsCheckBox;
    private SwitchMaterial preChatSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

        initViews();

        findViewById(R.id.chat_button).setOnClickListener(v -> {
            checkSettingsOpenScreen();
            clearBadge();
            HelpCrunch.trackEvent(
                    "Event chat opened",
                    "https://i.pinimg.com/originals/58/92/e7/5892e7f3cc64c8a912e2494a3ff77e08.jpg",
                    "Say Cheese");
        });

        findViewById(R.id.chat_custom_button).setOnClickListener(v -> openWithCustomSettings());
        findViewById(R.id.custom_user_data_button).setOnClickListener(v -> openCustomUserDataScreen());
        findViewById(R.id.user_data_button).setOnClickListener(v -> openUserDataScreen());
        findViewById(R.id.send_message_button).setOnClickListener(v -> openSendMessageScreen());

        findViewById(R.id.userData).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, UserDataActivity.class)));

        findViewById(R.id.logout_button).setOnClickListener(v -> logout());

        String version = "SDK: v" + HelpCrunch.getVersion();

        ((TextView) findViewById(R.id.version)).setText(version);

        ((TextView) findViewById(R.id.state)).setText(getStateString(HelpCrunch.getState()));

        registerReceiver(hcEventsBroadcastReceiver, new IntentFilter(HelpCrunch.EVENTS));
        registerReceiver(hcStateBroadcastReceiver, new IntentFilter(HelpCrunch.STATE));
    }

    private CharSequence getStateString(HelpCrunch.State state) {
        SpannableString stateStr = null;

        switch (state) {

            case IDLE:
                stateStr = getStateSpannableString("Idle", Color.GRAY);
                break;
            case READY:
                stateStr = getStateSpannableString("Ready", Color.GREEN);
                break;
            case LOADING:
                stateStr = getStateSpannableString("Loading...", Color.LTGRAY);
                break;
            case ERROR_BLOCKED_USER:
                stateStr = getStateSpannableString("User is blocked", Color.RED);
                break;
            case ERROR_INITIALIZATION:
                stateStr = getStateSpannableString("Initialization error", Color.RED);
                break;
            case ERROR_UNKNOWN:
                stateStr = getStateSpannableString("Error unknown", Color.RED);
                break;
            case HIDDEN:
                stateStr = getStateSpannableString("Hidden", Color.GRAY);
                break;
        }

        return stateStr;
    }

    @NotNull
    private SpannableString getStateSpannableString(String state, @ColorInt int color) {
        SpannableString stateStr;
        stateStr = new SpannableString(state);
        stateStr.setSpan(new ForegroundColorSpan(color), 0, stateStr.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return stateStr;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUnreadMessages();
        findViewById(R.id.logout_button).setEnabled(HelpCrunch.getUser() != null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(hcEventsBroadcastReceiver);
        unregisterReceiver(hcStateBroadcastReceiver);
    }

    private void logout() {

        setLogoutButtonParameters(View.VISIBLE, false);

        HelpCrunch.logout(new Callback<Object>() {
            @Override
            public void onSuccess(Object result) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                clearBadge();
                defaultOptionsCheckBox.setChecked(false);
                setLogoutButtonParameters(View.GONE, false);
            }

            @Override
            public void onError(@NotNull String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                setLogoutButtonParameters(View.GONE, true);
            }
        });
    }

    private void clearBadge() {
        badge1TextView.setText(null);
        badge1View.setVisibility(View.INVISIBLE);
    }

    private void checkSettingsOpenScreen() {
        if (defaultOptionsCheckBox.isChecked()) {
            showChat(null);
            return;
        }

        HCTheme theme = new HCTheme.Builder(HCTheme.Type.DEFAULT)
                .build();

        switch (((RadioGroup) findViewById(R.id.theme_group)).getCheckedRadioButtonId()) {
            case R.id.light:
                theme = new HCTheme.Builder(HCTheme.Type.DEFAULT).build();
                break;
            case R.id.dark:
                theme = new HCTheme.Builder(HCTheme.Type.DARK).build();
                break;
            case R.id.custom:
                HCMessageAreaTheme messageAreaTheme = new HCMessageAreaTheme.Builder()
                        .setButtonType(HCMessageAreaTheme.ButtonType.TEXT)
                        .build();

                theme = new HCTheme.Builder(R.color.main_color)
                        .setMessageAreaTheme(messageAreaTheme)
                        .build();
                break;
        }

        HCOptions.Builder optionsBuilder = new HCOptions.Builder()
                .setTheme(theme);

        if (preChatSwitch.isChecked()) {
            HCPreChatForm preChatForm = new HCPreChatForm.Builder()
                    .withName(true, null)
                    .withEmail(true)
                    .withPhone(false)
                    .build();

            optionsBuilder.setPreChatForm(preChatForm);
        }

        showChat(optionsBuilder.build());
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

                })
                .build();

        showChat(options);
    }

    private void showChat(@Nullable HCOptions options) {
        setChatScreenButtonParameters(View.GONE, View.VISIBLE, false);

        HelpCrunch.showChatScreen(options, new Callback<Object>() {
            @Override
            public void onError(@NotNull String message) {
                if (message.equals("user_blocked")) {
                    HCUser user = HelpCrunch.getUser();


                    if (user != null) {
                        String messageText = "You are a very bad person, " + user.getName() + ".\nPlease, uninstall the application.";

                        Toast.makeText(MainActivity.this, messageText, Toast.LENGTH_SHORT).show();
                    }
                } else if (message.equals("cant_open_chat")) {
                    Toast.makeText(MainActivity.this, "Can't open chat. Something is wrong", Toast.LENGTH_SHORT).show();
                }

                setChatScreenButtonParameters(View.VISIBLE, View.GONE, true);
            }

            @Override
            public void onSuccess(Object result) {
                setChatScreenButtonParameters(View.VISIBLE, View.GONE, true);
            }
        });
    }

    private void initViews() {
        badge1View = findViewById(R.id.badge_view);
        badge1TextView = findViewById(R.id.badge_tv);
        themeRadioGroup = findViewById(R.id.theme_group);
        defaultOptionsCheckBox = findViewById(R.id.default_options);
        defaultOptionsCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setThemeControlEnabled(!isChecked);
        });
        preChatSwitch = findViewById(R.id.pre_chat_switch);

        defaultOptionsCheckBox.setChecked(HelpCrunch.getUser() != null);
    }

    private void setThemeControlEnabled(boolean isEnabled) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("isEnabled", isEnabled);

        HelpCrunch.trackEvent("OptionsChanged", data);

        themeRadioGroup.setEnabled(isEnabled);
        preChatSwitch.setEnabled(isEnabled);

        for (int i = 0; i < themeRadioGroup.getChildCount(); i++) {
            themeRadioGroup.getChildAt(i).setEnabled(isEnabled);
        }
    }

    private void updateUnreadMessages() {
        HelpCrunch.getUnreadChatsCount(new Callback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                setVisibilityForUnreadMessagesBadge(result);
            }

            @Override
            public void onError(@NotNull String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setVisibilityForUnreadMessagesBadge(int count) {
        badge1View.setVisibility(count > 0 ? View.VISIBLE : View.INVISIBLE);
        String countString = String.valueOf(count);

        badge1TextView.setText(countString);
    }

    private void setChatScreenButtonParameters(int logoVisible, int progressVisible, boolean buttonEnabled) {
        findViewById(R.id.progress_open).setVisibility(progressVisible);
        findViewById(R.id.logo_btn).setVisibility(logoVisible);
        findViewById(R.id.chat_button).setEnabled(buttonEnabled);
    }

    private void setLogoutButtonParameters(int visible, boolean enabled) {
        findViewById(R.id.progress).setVisibility(visible);
        findViewById(R.id.logout_button).setEnabled(enabled);
    }

    private void openCustomUserDataScreen() {
        startActivity(new Intent(MainActivity.this, CustomUserDataActivity.class));
    }

    private void openUserDataScreen() {
        startActivity(new Intent(MainActivity.this, UserDataActivity.class));
    }

    private void openSendMessageScreen() {
        startActivity(new Intent(MainActivity.this, SendMessageActivity.class));
    }
}
