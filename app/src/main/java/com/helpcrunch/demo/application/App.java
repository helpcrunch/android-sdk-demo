package com.helpcrunch.demo.application;

import androidx.multidex.MultiDexApplication;

import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.models.user.HCUser;

import org.jetbrains.annotations.NotNull;

public class App extends MultiDexApplication {
    public static final String ORGANIZATION = "org"; //your organization name
    public static final int APP_ID = 0; // your app id
    public static final String SECRET = "secret"; // your app secret

    @Override
    public void onCreate() {
        super.onCreate();

        HelpCrunch.initialize(ORGANIZATION, APP_ID, SECRET, getMyUser());
        // or
        // HelpCrunch.initialize(ORGANIZATION, APP_ID, SECRET);
    }

    @NotNull
    public static HCUser getMyUser() {
        return new HCUser.Builder()
                .withName("Name")
                .withEmail("email@email.com")
                .withUserId("someId_1")
                .withCompany("company name")
                .build();
    }
}
