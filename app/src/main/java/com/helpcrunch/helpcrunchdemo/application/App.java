package com.helpcrunch.helpcrunchdemo.application;

import androidx.multidex.MultiDexApplication;

import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.repository.models.user.HCUser;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class App extends MultiDexApplication {
    private static final String ORGANISATION = "mobile";
    private static final int APP_ID = 2889;
    private static final String SECRET = "BT4na/0/fHk6d1jtg0qKiK5GoxXf1/GgP0ay0ps2UiWJPfdPeUDFUYwnIjBFO49oilOKx+EMg2Tw+BJsS/hI6g==";

    @Override
    public void onCreate() {
        super.onCreate();

        HCUser user = getTestUser();

        HelpCrunch.initialize(
                this,
                ORGANISATION,
                APP_ID,
                SECRET,
                user
        );
    }

    @NotNull
    public static HCUser getTestUser() {
        Map<String, String> customData = new HashMap<>();
        customData.put("time", String.valueOf(System.currentTimeMillis()));

        return new HCUser.Builder()
                .withName("John Doe")
                .withEmail("john.doe@any.com")
                .withUserId("test_user_android")
                .withCompany("Lorem Ipsum")
                .withPhone("+44 (203) 514-1245")
                .withCustomData(customData)
                .build();
    }
}
