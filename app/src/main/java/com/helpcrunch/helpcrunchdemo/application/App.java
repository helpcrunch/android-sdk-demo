package com.helpcrunch.helpcrunchdemo.application;

import android.app.Application;

import com.helpcrunch.helpcrunchdemo.R;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.HelpCrunchOptions;
import com.helpcrunch.library.model.User;
import com.helpcrunch.library.model.UserBuilder;
import com.helpcrunch.library.ui.design.HelpCrunchDesign;

public class App extends Application {
    private static final String ORGANISATION = "mobile";
    private static final int APP_ID = 2889;
    private static final String SECRET = "BT4na/0/fHk6d1jtg0qKiK5GoxXf1/GgP0ay0ps2UiWJPfdPeUDFUYwnIjBFO49oilOKx+EMg2Tw+BJsS/hI6g==";

    @Override
    public void onCreate() {
        super.onCreate();

        HelpCrunchOptions options = new HelpCrunchOptions()
                .setDesign(new HelpCrunchDesign())
                .setFcmIcon(R.drawable.fcm_custom_icon)
                .setNotificationsChannelTitle("My super company")
                .setNotificationsLargeIconBgColor(R.color.colorAccent);

        User user = new UserBuilder()
                .withEmail("al@g.day")
                .withName("Test Demo")
                .build();

        HelpCrunch.initializeWithOptions(
                this,
                ORGANISATION,
                APP_ID,
                SECRET,
                options,
                user
        );
    }
}
