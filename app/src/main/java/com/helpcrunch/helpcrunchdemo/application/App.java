package com.helpcrunch.helpcrunchdemo.application;

import androidx.multidex.MultiDexApplication;

import com.helpcrunch.library.core.HelpCrunch;

class App extends MultiDexApplication {
    public static final String ORGANISATION = "mobile";
    public static final int APP_ID = 2889;
    public static final String SECRET = "BT4na/0/fHk6d1jtg0qKiK5GoxXf1/GgP0ay0ps2UiWJPfdPeUDFUYwnIjBFO49oilOKx+EMg2Tw+BJsS/hI6g==";

    @Override
    public void onCreate() {
        super.onCreate();

        HelpCrunch.initialize(ORGANISATION, APP_ID, SECRET);

    }
}
