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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CustomUserDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_user_data);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText customKey1 = findViewById(R.id.field1EditText);
        final EditText customKey2 = findViewById(R.id.field2EditText);
        final EditText customKey3 = findViewById(R.id.field3EditText);
        final EditText customData1 = findViewById(R.id.data1EditText);
        final EditText customData2 = findViewById(R.id.data2EditText);
        final EditText customData3 = findViewById(R.id.data3EditText);
        int i = 0;

        findViewById(R.id.saveUserDataButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> customData = new HashMap<String, Object>();
                addData(customData, customKey1.getText().toString(), customData1.getText().toString());
                addData(customData, customKey2.getText().toString(), customData2.getText().toString());
                addData(customData, customKey3.getText().toString(), customData3.getText().toString());

                HelpCrunch.updateUser(CustomUserDataActivity.this, new UserBuilder().withCustomData(customData).build(), new Callback<User>() {
                    @Override
                    public void onSuccess(User result) {
                        HCViewUtils.toast(CustomUserDataActivity.this, getString(R.string.data_saved));
                    }

                    @Override
                    public void onError(Exception e) {
                        HCViewUtils.toast(CustomUserDataActivity.this, getString(R.string.something_wrong));
                    }
                });
            }
        });

        User currentUser = HelpCrunch.getStorage(CustomUserDataActivity.this).loadUser();
        if (currentUser == null) return;

        JSONObject customDataJsonObject = currentUser.getCustomDataJsonObject();
        if (customDataJsonObject == null) return;

        for (Iterator<String> iter = customDataJsonObject.keys(); iter.hasNext(); ) {
            String customKey = iter.next();
            try {
                String customData = customDataJsonObject.getString(customKey);
                if (i == 0) {
                    customKey1.setText(customKey);
                    customData1.setText(customData);
                } else if (i == 1) {
                    customKey2.setText(customKey);
                    customData2.setText(customData);
                } else {
                    customKey3.setText(customKey);
                    customData3.setText(customData);
                }
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
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

    private void addData(Map<String, Object> customData, String key, String object) {
        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(object)) {
            customData.put(key, object);
        }
    }
}
