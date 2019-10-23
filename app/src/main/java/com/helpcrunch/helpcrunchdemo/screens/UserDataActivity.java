package com.helpcrunch.helpcrunchdemo.screens;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.helpcrunch.helpcrunchdemo.R;
import com.helpcrunch.library.core.Callback;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.repository.models.user.HCUser;
import com.helpcrunch.library.utils.extensions.ContextKt;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;


public class UserDataActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText userIdEditText;
    private EditText companyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.change_user_data);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        userIdEditText = findViewById(R.id.userIdEditText);
        companyEditText = findViewById(R.id.companyEditText);

        HCUser currentUser = HelpCrunch.getUser();

        if (currentUser != null) {
            nameEditText.setText(currentUser.getName());
            emailEditText.setText(currentUser.getEmail());
            phoneEditText.setText(currentUser.getPhone());
            userIdEditText.setText(currentUser.getUserId());
            companyEditText.setText(currentUser.getCompany());
        }

        findViewById(R.id.saveUserDataButton).setOnClickListener(view -> updateUserData());
    }

    private void updateUserData() {
        if(HelpCrunch.getUser() == null){
            ContextKt.demoToast(this, "There is no user yet");

            return;
        }

        final String username = nameEditText.getText().toString();

        if (!TextUtils.isEmpty(username.trim())) {
            String registerUserId = userIdEditText.getText().toString();

            HashMap<String, Object> customData = new HashMap<>();
            customData.put("CUSTOM_TIME", System.currentTimeMillis());

            HCUser registerUser = new HCUser.Builder()
                    .withName(username)
                    .withEmail(emailEditText.getText().toString())
                    .withPhone(phoneEditText.getText().toString())
                    .withUserId(registerUserId)
                    .withCustomData(customData)
                    .withCompany(companyEditText.getText().toString())
                    .build();

            findViewById(R.id.progress).setVisibility(View.VISIBLE);
            findViewById(R.id.saveUserDataButton).setEnabled(false);

            HelpCrunch.updateUser(registerUser, new Callback<HCUser>() {
                @Override
                public void onSuccess(HCUser result) {
                    ContextKt.toast(UserDataActivity.this, getString(R.string.data_saved));
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    findViewById(R.id.saveUserDataButton).setEnabled(true);
                }

                @Override
                public void onError(@NotNull String message) {
                    ContextKt.toast(UserDataActivity.this, getString(R.string.something_wrong));
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    findViewById(R.id.saveUserDataButton).setEnabled(true);
                }

            });
        } else {
            ContextKt.toast(UserDataActivity.this, getString(R.string.error_id_is_empty));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
