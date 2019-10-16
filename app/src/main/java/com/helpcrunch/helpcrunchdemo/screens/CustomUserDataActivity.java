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
import com.helpcrunch.library.models.remote.HCUser;
import com.helpcrunch.library.utils.extensions.ContextKt;

import org.jetbrains.annotations.NotNull;
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

// ============================================= Before v2.0 =============================================
//              Map<String, Object> customData = new HashMap<String, Object>();
// ========================================================================================================

                HashMap<String, Object> customData = new HashMap<>();
                addData(customData, customKey1.getText().toString(), customData1.getText().toString());
                addData(customData, customKey2.getText().toString(), customData2.getText().toString());
                addData(customData, customKey3.getText().toString(), customData3.getText().toString());

// ============================================= Before v2.0 =============================================
//                HelpCrunch.updateUser(CustomUserDataActivity.this, new UserBuilder().withCustomData(customData).build(), new Callback<User>() {
//                    @Override
//                    public void onSuccess(User result) {
//                        HCViewUtils.toast(CustomUserDataActivity.this, getString(R.string.data_saved));
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        HCViewUtils.toast(CustomUserDataActivity.this, getString(R.string.something_wrong));
//                    }
//                });
// ========================================================================================================

                HCUser user = new HCUser.Builder()
                        .withCustomData(customData)
                        .build();

                HelpCrunch.updateUser(user, new Callback<HCUser>() {
                    @Override
                    public void onSuccess(HCUser result) {
                        ContextKt.toast(CustomUserDataActivity.this, getString(R.string.data_saved));
                    }

                    @Override
                    public void onError(@NotNull String message) {
                        ContextKt.toast(CustomUserDataActivity.this, getString(R.string.something_wrong));
                    }
                });
            }
        });

// ============================================= Before v2.0 =============================================
//        User currentUser = HelpCrunch.getStorage(CustomUserDataActivity.this).loadUser();
// ========================================================================================================

        HCUser currentUser = HelpCrunch.getUser();
        if (currentUser == null) return;

        JSONObject customDataJsonObject = currentUser.getCustomDataJsonObject();
        if (customDataJsonObject == null) return;

        for (Iterator<String> iterator = customDataJsonObject.keys(); iterator.hasNext(); ) {
            String customKey = iterator.next();
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
