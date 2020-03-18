package com.helpcrunch.helpcrunchdemo.application;

import androidx.multidex.MultiDexApplication;

import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.repository.models.user.HCUser;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class App extends MultiDexApplication {
    public static final String ORGANIZATION = "mobile";
    public static final int APP_ID = 2889;
    public static final String SECRET = "BT4na/0/fHk6d1jtg0qKiK5GoxXf1/GgP0ay0ps2UiWJPfdPeUDFUYwnIjBFO49oilOKx+EMg2Tw+BJsS/hI6g==";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
