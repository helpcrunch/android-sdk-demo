package com.helpcrunch.helpcrunchdemo.application;

import androidx.multidex.MultiDexApplication;

import com.helpcrunch.library.core.Callback;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.repository.models.user.HCUser;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class App extends MultiDexApplication {
    public static final String ORGANIZATION = "domain";
    public static final int APP_ID = 0;
    public static final String SECRET =  "token";

    @Override
    public void onCreate() {
        super.onCreate();

        HelpCrunch.initialize(ORGANIZATION, APP_ID, SECRET, null, null, new Callback<Object>() {
            @Override
            public void onSuccess(Object result) {
                HCUser registerUser = new HCUser.Builder()
                        .withUserId(UUID.randomUUID().toString())
                        .build();

                HelpCrunch.updateUser(registerUser);
            }
        });



    }
}
