package com.helpcrunch.helpcrunchdemo.screens;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.helpcrunch.helpcrunchdemo.R;
import com.helpcrunch.library.core.Callback;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.repository.models.user.HCUser;
import com.helpcrunch.library.utils.extensions.ContextKt;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CustomUserDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_user_data);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.change_custom_user_data);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findViewById(R.id.saveUserDataButton).setOnClickListener(view -> {
            saveCustomData();
        });

        fillData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_custom_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        } else if (item.getItemId() == R.id.add_row) {
            addRow(null, null);
        }
        return super.onOptionsItemSelected(item);
    }


    private void fillData() {
        HCUser currentUser = HelpCrunch.getUser();

        if (currentUser == null) return;

        Map<String, Object> data = currentUser.getCustomData();

        if (data != null) {
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                addRow(entry.getKey(), entry.getValue().toString());
            }
        }
    }

    private void saveCustomData() {
        if(HelpCrunch.getUser() == null){
            ContextKt.demoToast(this, "There is no user yet");

            return;
        }

        HashMap<String, Object> customData = new HashMap<>();
        LinearLayout form = findViewById(R.id.registration_form);

        getNewData(customData, form);

        HCUser user = new HCUser.Builder()
                .withCustomData(customData)
                .build();

        findViewById(R.id.progress).setVisibility(View.VISIBLE);
        findViewById(R.id.saveUserDataButton).setEnabled(false);

        HelpCrunch.updateUser(user, new Callback<HCUser>() {
            @Override
            public void onSuccess(HCUser result) {
                ContextKt.toast(CustomUserDataActivity.this, getString(R.string.data_saved));
                findViewById(R.id.progress).setVisibility(View.GONE);
                findViewById(R.id.saveUserDataButton).setEnabled(true);
            }

            @Override
            public void onError(@NotNull String message) {
                ContextKt.toast(CustomUserDataActivity.this, getString(R.string.something_wrong));
                findViewById(R.id.progress).setVisibility(View.GONE);
                findViewById(R.id.saveUserDataButton).setEnabled(true);
            }
        });
    }

    private void getNewData(HashMap<String, Object> customData, LinearLayout form) {
        for (int i = 0; i < form.getChildCount(); i++) {
            View child = form.getChildAt(i);

            if (child instanceof LinearLayout) {
                String key = ((EditText) child.findViewById(R.id.key)).getText().toString();
                String value = ((EditText) child.findViewById(R.id.value)).getText().toString();

                addData(customData, key, value);
            }
        }
    }

    private void addRow(String key, String value) {
        LinearLayout form = findViewById(R.id.registration_form);

        View view = LayoutInflater.from(this).inflate(R.layout.layout_custom_user_data_row, null);

        ((EditText) view.findViewById(R.id.key)).setText(key);
        ((EditText) view.findViewById(R.id.value)).setText(value);

        form.addView(view);
    }

    private void addData(Map<String, Object> customData, String key, String object) {
        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(object)) {
            customData.put(key, object);
        }
    }
}
