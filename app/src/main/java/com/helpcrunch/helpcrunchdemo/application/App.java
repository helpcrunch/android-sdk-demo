package com.helpcrunch.helpcrunchdemo.application;

import androidx.multidex.MultiDexApplication;

import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.models.user.HCUser;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class App extends MultiDexApplication {
    public static final String ORGANIZATION = "mobile";
    public static final int APP_ID = 2889;
    public static final String SECRET = "BT4na/0/fHk6d1jtg0qKiK5GoxXf1/GgP0ay0ps2UiWJPfdPeUDFUYwnIjBFO49oilOKx+EMg2Tw+BJsS/hI6g==";

    @Override
    public void onCreate() {
        super.onCreate();

        HelpCrunch.initialize(ORGANIZATION, APP_ID, SECRET, getMyUser());
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
