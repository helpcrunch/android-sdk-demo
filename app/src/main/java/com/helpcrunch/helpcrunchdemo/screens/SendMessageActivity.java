package com.helpcrunch.helpcrunchdemo.screens;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.helpcrunch.helpcrunchdemo.R;
import com.helpcrunch.library.core.HelpCrunch;

import java.util.HashMap;

public class SendMessageActivity extends AppCompatActivity {
    private BroadcastReceiver hcEventsBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            HelpCrunch.Event event = (HelpCrunch.Event) intent.getSerializableExtra(HelpCrunch.EVENT_TYPE);
            HashMap<String, String> data = (HashMap<String, String>) intent.getSerializableExtra(HelpCrunch.EVENT_DATA);

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

    private void initViews() {
        findViewById(R.id.sendText).setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String text = ((EditText) findViewById(R.id.message_text)).getText().toString();

        if (TextUtils.isEmpty(text)) {
            Toast.makeText(SendMessageActivity.this, "Text is empty", Toast.LENGTH_SHORT).show();

            return;
        }

        boolean isForceNewChat = ((CheckBox) findViewById(R.id.force_new_chat)).isChecked();

        HelpCrunch.sendMessage(text, isForceNewChat);
    }

    private void addMessage(String resultData) {
        LinearLayout messagesView = findViewById(R.id.messages);

        AppCompatTextView textView = new AppCompatTextView(this);
        textView.setText(resultData);

        messagesView.addView(textView);
    }

}
