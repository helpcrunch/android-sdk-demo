package com.helpcrunch.helpcrunchdemo.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.helpcrunch.helpcrunchdemo.R;
import com.helpcrunch.library.core.Callback;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.model.User;
import com.helpcrunch.library.model.UserBuilder;
import com.helpcrunch.library.utils.HCViewUtils;

import java.util.HashMap;
import java.util.Map;

public class UserDataActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText userIdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        userIdEditText = findViewById(R.id.userIdEditText);
        User currentUser = HelpCrunch.getStorage(this).loadUser();

        if (currentUser != null) {
            nameEditText.setText(currentUser.getName());
            emailEditText.setText(currentUser.getEmail());
            phoneEditText.setText(currentUser.getPhone());
            userIdEditText.setText(currentUser.getUserId());
        }
        findViewById(R.id.saveUserDataButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = nameEditText.getText().toString();
                if (!TextUtils.isEmpty(username.trim())) {

                    String registerUserId = userIdEditText.getText().toString();

                    Map<String, Object> customData = new HashMap<String, Object>();
                    customData.put("CUSTOM_TIME", System.currentTimeMillis());

                    User registerUser = new UserBuilder()
                            .withName(username)
                            .withEmail(emailEditText.getText().toString())
                            .withPhone(phoneEditText.getText().toString())
                            .withUserID(registerUserId)
                            .withCustomData(customData)
                            .withOrganization("My organization")
                            .build();

                    HelpCrunch.updateUser(UserDataActivity.this, registerUser, new Callback<User>() {
                        @Override
                        public void onSuccess(User result) {
                            HCViewUtils.toast(UserDataActivity.this, getString(R.string.data_saved));

                        }

                        @Override
                        public void onError(Exception e) {
                            HCViewUtils.toast(UserDataActivity.this, getString(R.string.something_wrong));

                        }
                    });
                } else {
                    HCViewUtils.toast(UserDataActivity.this, getString(R.string.name_is_empty));
                }
            }
        });
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
