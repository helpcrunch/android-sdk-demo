package com.helpcrunch.helpcrunchdemo.screens.launch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.helpcrunch.helpcrunchdemo.R;
import com.helpcrunch.helpcrunchdemo.application.App;
import com.helpcrunch.helpcrunchdemo.screens.MainActivity;
import com.helpcrunch.helpcrunchdemo.screens.UserDataActivity;
import com.helpcrunch.helpcrunchdemo.utils.dialogs.TestUsersDialogFragment;
import com.helpcrunch.library.core.Callback;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.repository.models.user.HCUser;
import com.helpcrunch.library.utils.extensions.StringKt;
import com.helpcrunch.library.utils.views.avatar_view.HCAvatarView;

import org.jetbrains.annotations.NotNull;

public class LaunchActivity extends AppCompatActivity {
    private static int USER_CODE = 101;

    private EditText organizationEditText;
    private EditText appIdEditText;
    private EditText secretEditText;
    private TextView setUserText;

    private HCUser user;
    SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        sp = this.getSharedPreferences("last_credentials", Context.MODE_PRIVATE);

        initViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == USER_CODE && resultCode == Activity.RESULT_OK) {
            setUserText.setText("Change user data");

            user = new HCUser.Builder()
                    .withName(data.getStringExtra("username"))
                    .withEmail(data.getStringExtra("email"))
                    .withPhone(data.getStringExtra("phone"))
                    .withUserId(data.getStringExtra("registerUserId"))
                    .withCompany(data.getStringExtra("company"))
                    .build();

            setUserView(user);
        }
    }

    private void setUserView(HCUser user) {
        findViewById(R.id.userContainer).setVisibility(View.VISIBLE);
        HCAvatarView avatarView = findViewById(R.id.avatar);
        TextView name = findViewById(R.id.name);
        TextView userId = findViewById(R.id.user_id);

        String namePlaceholder = StringKt.getPlaceholderText(user.getName(), true);
        avatarView.setBorderWidth(0);
        avatarView.setBackgroundPlaceholderColor(-12483341);
        avatarView.setTextColor(Color.WHITE);
        avatarView.setPlaceholderText(namePlaceholder);
        String id = user.getUserId() == null || TextUtils.isEmpty(user.getUserId()) ? "N/A" : user.getUserId();

        name.setText(user.getName());
        userId.setText(id);
    }

    private void initViews() {
        organizationEditText = findViewById(R.id.organizationEditText);
        appIdEditText = findViewById(R.id.appIdEditText);
        secretEditText = findViewById(R.id.secretEditText);
        setUserText = findViewById(R.id.setUserText);

        loadLastCredentials();

        findViewById(R.id.launch).setOnClickListener(v -> {
            initSdk();
        });

        findViewById(R.id.userContainerSet).setOnClickListener(v -> {
            openUserDataScreen();
        });

        findViewById(R.id.logo).setOnClickListener(v -> {
            setDefaultCredentials();
        });

        findViewById(R.id.userContainer).setOnClickListener(v -> {
            openUserDataDialog();
        });
    }

    private void openUserDataDialog() {
        if (user == null) return;

        new TestUsersDialogFragment(user)
                .show(getSupportFragmentManager(), "Users");
    }

    private void loadLastCredentials() {
        String organization = sp.getString("organization", null);
        String appId = sp.getString("appId", null);
        String secret = sp.getString("secret", null);

        organizationEditText.setText(organization);
        appIdEditText.setText(appId);
        secretEditText.setText(secret);
    }

    private void setDefaultCredentials() {
        organizationEditText.setText(App.ORGANIZATION);
        secretEditText.setText(App.SECRET);
        appIdEditText.setText(String.valueOf(App.APP_ID));
    }

    private void initSdk() {
        String organization = organizationEditText.getText().toString();
        String appId = appIdEditText.getText().toString();
        String secret = secretEditText.getText().toString();

        if (TextUtils.isEmpty(organization) || TextUtils.isEmpty(appId) || TextUtils.isEmpty(secret)) {
            Toast.makeText(this, "Unable to initialize with current credentials", Toast.LENGTH_SHORT).show();
        } else {
            try {
                int appIdInt = Integer.parseInt(appId);

                findViewById(R.id.progress).setVisibility(View.VISIBLE);

                HelpCrunch.initialize(organization, appIdInt, secret, user, null, new Callback<Object>() {
                    @Override
                    public void onSuccess(Object result) {
                        saveLastCredentials();
                        openMainScreen();
                        findViewById(R.id.progress).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(@NotNull String message) {
                        Toast.makeText(LaunchActivity.this, message, Toast.LENGTH_SHORT).show();
                        findViewById(R.id.progress).setVisibility(View.GONE);
                    }
                });
            } catch (Exception e) {
                Toast.makeText(this, "Unable to initialize with current credentials", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private void openUserDataScreen() {
        Intent userScreenIntent = new Intent(this, UserDataActivity.class);
        userScreenIntent.putExtra("isForLaunch", true);

        startActivityForResult(userScreenIntent, USER_CODE);
    }

    private void openMainScreen() {
        startActivity(new Intent(this, MainActivity.class));

        finish();
    }

    private void saveLastCredentials() {
        String organization = organizationEditText.getText().toString();
        String appId = appIdEditText.getText().toString();
        String secret = secretEditText.getText().toString();

        sp.edit()
                .putString("organization", organization)
                .putString("appId", appId)
                .putString("secret", secret)
                .apply();
    }
}
