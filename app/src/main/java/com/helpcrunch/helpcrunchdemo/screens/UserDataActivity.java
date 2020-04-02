package com.helpcrunch.helpcrunchdemo.screens;

import android.content.Intent;
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

    private boolean isForLaunch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        isForLaunch = getIntent().getBooleanExtra("isForLaunch", false);

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

        findViewById(R.id.saveUserDataButton).setOnClickListener(view -> {
                    if (isForLaunch) {
                        returnUserData();
                    } else {
                        updateUserData();
                    }
                }
        );
    }

    private void returnUserData() {
        final String username = nameEditText.getText().toString().trim();
        final String email = emailEditText.getText().toString().trim();
        final String phone = phoneEditText.getText().toString().trim();
        final String company = companyEditText.getText().toString().trim();
        final String registerUserId = userIdEditText.getText().toString().trim();

        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("email", email);
        bundle.putString("phone", phone);
        bundle.putString("company", company);
        bundle.putString("registerUserId", registerUserId);

        Intent intent = new Intent();
        intent.putExtras(bundle);

        setResult(RESULT_OK, intent);
        finish();
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
                    .withName(username)
                    .withEmail(email)
                    .withPhone(phone)
                    .withUserId(registerUserId)
                    .withCustomData(customData)
                    .withCompany(company)
                    .build();

            findViewById(R.id.progress).setVisibility(View.VISIBLE);
            findViewById(R.id.saveUserDataButton).setEnabled(false);

            HelpCrunch.updateUser(registerUser, new Callback<HCUser>() {
                @Override
                public void onSuccess(HCUser result) {
                    Toast.makeText(UserDataActivity.this, getString(R.string.data_saved), Toast.LENGTH_SHORT).show();
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    findViewById(R.id.saveUserDataButton).setEnabled(true);
                }

                @Override
                public void onError(@NotNull String message) {
                    Toast.makeText(UserDataActivity.this, getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                    findViewById(R.id.progress).setVisibility(View.GONE);
                    findViewById(R.id.saveUserDataButton).setEnabled(true);
                }

            });
        } else {
            Toast.makeText(UserDataActivity.this, getString(R.string.error_id_is_empty), Toast.LENGTH_SHORT).show();
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
