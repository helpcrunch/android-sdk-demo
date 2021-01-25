package com.helpcrunch.helpcrunchdemo.screens;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.helpcrunch.helpcrunchdemo.R;
import com.helpcrunch.library.core.Callback;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.models.user.HCUser;

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

        findViewById(R.id.save_user_data_button).setOnClickListener(view -> updateUserData());

        findViewById(R.id.logout_button).setOnClickListener(view -> logout());
    }

    private void logout() {
        setLogoutButtonParameters(View.VISIBLE, false);

        HelpCrunch.logout(new Callback<Object>() {
            @Override
            public void onSuccess(Object result) {
                Toast.makeText(UserDataActivity.this, "Success", Toast.LENGTH_SHORT).show();
                setLogoutButtonParameters(View.GONE, false);
            }

            @Override
            public void onError(@NotNull String message) {
                Toast.makeText(UserDataActivity.this, message, Toast.LENGTH_SHORT).show();
                setLogoutButtonParameters(View.GONE, true);
            }
        });
    }

    private void updateUserData() {
        if (HelpCrunch.getUser() == null) {
            Toast.makeText(this, "There is no user yet", Toast.LENGTH_SHORT).show();

            return;
        }

        final String username = nameEditText.getText().toString().trim();
        final String email = emailEditText.getText().toString().trim();
        final String phone = phoneEditText.getText().toString().trim();
        final String company = companyEditText.getText().toString().trim();
        final String registerUserId = userIdEditText.getText().toString().trim();

        if (!TextUtils.isEmpty(username.trim())) {
            HashMap<String, Object> customData = new HashMap<>();
            customData.put("CUSTOM_TIME", System.currentTimeMillis());

            HCUser registerUser = new HCUser.Builder()
                    .withUserId(registerUserId)
                    .withName(username)
                    .withEmail(email)
                    .withPhone(phone)
                    .withCustomData(customData)
                    .withCompany(company)
                    .build();

            setSaveUserDataButtonParameters(View.VISIBLE, false);

            HelpCrunch.updateUser(registerUser, new Callback<HCUser>() {
                @Override
                public void onSuccess(HCUser result) {
                    Toast.makeText(UserDataActivity.this, getString(R.string.data_saved), Toast.LENGTH_SHORT).show();
                    setSaveUserDataButtonParameters(View.GONE, true);
                }

                @Override
                public void onError(@NotNull String message) {
                    Toast.makeText(UserDataActivity.this, message, Toast.LENGTH_SHORT).show();
                    setSaveUserDataButtonParameters(View.GONE, true);
                }
            });
        } else {
            Toast.makeText(UserDataActivity.this, getString(R.string.error_id_is_empty), Toast.LENGTH_SHORT).show();
        }
    }

    private void setSaveUserDataButtonParameters(int visible, boolean enabled) {
        findViewById(R.id.save_user_data_button_progress).setVisibility(visible);
        findViewById(R.id.save_user_data_button).setEnabled(enabled);
    }

    private void setLogoutButtonParameters(int visible, boolean enabled) {
        findViewById(R.id.progress).setVisibility(visible);
        findViewById(R.id.logout_button).setEnabled(enabled);
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
