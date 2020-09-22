package com.helpcrunch.helpcrunchdemo.application;

import androidx.multidex.MultiDexApplication;

import com.helpcrunch.library.core.Callback;
import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.models.user.HCUser;
import com.helpcrunch.library.core.options.HCOptions;
import com.helpcrunch.library.core.options.design.HCTheme;

import org.jetbrains.annotations.NotNull;

public class App extends MultiDexApplication {
    public static final String ORGANIZATION = "mobile";
    public static final int APP_ID = 2889;
    public static final String SECRET = "BT4na/0/fHk6d1jtg0qKiK5GoxXf1/GgP0ay0ps2UiWJPfdPeUDFUYwnIjBFO49oilOKx+EMg2Tw+BJsS/hI6g==";

    @Override
    public void onCreate() {
        super.onCreate();

//        HelpCrunch.initialize(ORGANIZATION, APP_ID, SECRET, getMyUser());

        HCUser user = new HCUser.Builder().withUserId("87082229922").build();
        HCTheme theme = new HCTheme.Builder(HCTheme.Type.DEFAULT).build();
        HCOptions options = new HCOptions.Builder().setTheme(theme).build();
        HelpCrunch.initialize("apprekassa", 1, "h3ZRtcPhr0p3ynZ/YySIbcUvqcFM+hpoMOkb9GtOUL1jttVkVBkDScgrtcCkvioKAmtihcwG9UVIczbMeITpBw==",
                user, options, new Callback<Object>() {
                    @Override
                    public void onSuccess(Object result) {
//                        callbackContext.success(0);
                    }

                    @Override
                    public void onError(@NotNull String message) {
//                        callbackContext.error(message);
                    }
                });
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
