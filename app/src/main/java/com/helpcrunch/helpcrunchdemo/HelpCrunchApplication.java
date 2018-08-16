package com.helpcrunch.helpcrunchdemo;

import android.app.Application;

import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.HelpCrunchOptions;
import com.helpcrunch.library.ui.design.HelpCrunchDesign;

public class HelpCrunchApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        HelpCrunchOptions options = new HelpCrunchOptions()
                .setDesign(new HelpCrunchDesign())
                .setFcmIcon(R.drawable.fcm_custom_icon)
                .setNotificationsChannelTitle("My super company")
                .setNotificationsLargeIconBgColor(R.color.colorAccent);

        HelpCrunch.initializeWithOptions(
                this,
                "mobile",
                2889,
                "BT4na/0/fHk6d1jtg0qKiK5GoxXf1/GgP0ay0ps2UiWJPfdPeUDFUYwnIjBFO49oilOKx+EMg2Tw+BJsS/hI6g==",
                options
        );
    }
}
