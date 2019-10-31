package com.helpcrunch.helpcrunchdemo.application;

import androidx.multidex.MultiDexApplication;

import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.repository.models.user.HCUser;

public class App extends MultiDexApplication {
    private static final String ORGANISATION = "mobile";
    private static final int APP_ID = 2889;
    private static final String SECRET = "BT4na/0/fHk6d1jtg0qKiK5GoxXf1/GgP0ay0ps2UiWJPfdPeUDFUYwnIjBFO49oilOKx+EMg2Tw+BJsS/hI6g==";

    @Override
    public void onCreate() {
        super.onCreate();

        HCUser user = new HCUser.Builder()
                .withName("Lorem User")
                .withEmail("lorem.user@dolor.sit")
                .withUserId("u123a")
                .withCompany("Example Organization")
                .withPhone("+4580209020")
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
