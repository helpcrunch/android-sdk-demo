package com.helpcrunch.helpcrunchdemo.application;

import androidx.multidex.MultiDexApplication;

import com.helpcrunch.library.core.Callback;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.repository.models.user.HCUser;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class App extends MultiDexApplication {
    public static final String ORGANIZATION = "hunk";
    public static final int APP_ID = 6209;
    public static final String SECRET =  "e8NVS11RwC7v6R4QzDC/+yetiLI9PVgEEfauE8sTWP+iTdsa+/5+hHKlbEpSziiXTpjq4Nsue0e/JEII9FztRA==";

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
