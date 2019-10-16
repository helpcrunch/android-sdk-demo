package com.helpcrunch.helpcrunchdemo.application;

import androidx.multidex.MultiDexApplication;

import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.models.remote.HCUser;

public class App extends MultiDexApplication {
    private static final String ORGANISATION = "mobile";
    private static final int APP_ID = 2889;
    private static final String SECRET = "BT4na/0/fHk6d1jtg0qKiK5GoxXf1/GgP0ay0ps2UiWJPfdPeUDFUYwnIjBFO49oilOKx+EMg2Tw+BJsS/hI6g==";

    @Override
    public void onCreate() {
        super.onCreate();

        HCUser user = new HCUser.Builder()
                .withEmail("al@g.day")
                .withName("Test Demo")
                .build();

        HelpCrunch.initialize(
                this,
                ORGANISATION,
                APP_ID,
                SECRET,
                user
        );
    }
}
