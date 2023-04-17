package com.helpcrunch.demo.screens;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.helpcrunch.demo.R;
import com.helpcrunch.library.core.HelpCrunch;

import java.util.HashMap;

public class SendMessageActivity extends AppCompatActivity {
    private final BroadcastReceiver hcEventsBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            HelpCrunch.Event event = (HelpCrunch.Event) intent.getSerializableExtra(HelpCrunch.EVENT_TYPE);
            HashMap<String, String> data = (HashMap<String, String>) intent.getSerializableExtra(HelpCrunch.EVENT_DATA);

            setSendButtonParameters(View.VISIBLE, View.GONE, true);

            if (event == null) {
                Log.w(HelpCrunch.EVENTS, "Can't receive data");
                return;
            }

            if (event == HelpCrunch.Event.MESSAGE_SENDING) {
                if (data != null) {
                    String error = data.get("error");
                    String resultData = data.get("data");

                    if (error != null) {
                        Toast.makeText(SendMessageActivity.this, error, Toast.LENGTH_SHORT).show();
                    } else {
                        addMessage(resultData);
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        initViews();

        registerReceiver(hcEventsBroadcastReceiver, new IntentFilter(HelpCrunch.EVENTS));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(hcEventsBroadcastReceiver);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.send_message);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findViewById(R.id.send_button).setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String text = ((EditText) findViewById(R.id.message_text)).getText().toString();

        if (TextUtils.isEmpty(text)) {
            Toast.makeText(SendMessageActivity.this, "Text is empty", Toast.LENGTH_SHORT).show();

            return;
        }

        boolean isForceNewChat = ((CheckBox) findViewById(R.id.force_new_chat)).isChecked();

        HelpCrunch.sendMessage(text, isForceNewChat);

        setSendButtonParameters(View.GONE, View.VISIBLE, false);
    }

    private void addMessage(String resultData) {
        LinearLayout messagesView = findViewById(R.id.messages);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_hc_send, null);
        drawable.setColorFilter(ContextCompat.getColor(this, R.color.colorBlue), PorterDuff.Mode.SRC_IN);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        AppCompatTextView textView = new AppCompatTextView(this);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setCompoundDrawables(drawable, null, null, null);
        textView.setText(resultData);
        textView.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.messages_padding));
        messagesView.addView(textView);
    }

    private void setSendButtonParameters(int iconVisible, int progressVisible, boolean enabled) {
        findViewById(R.id.send_button_icon).setVisibility(iconVisible);
        findViewById(R.id.send_button_progress).setVisibility(progressVisible);
        findViewById(R.id.send_button).setEnabled(enabled);
    }
}
