package com.helpcrunch.demo.application;

import androidx.multidex.MultiDexApplication;

import com.helpcrunch.library.core.HelpCrunch;
import com.helpcrunch.library.core.models.user.HCUser;

import org.jetbrains.annotations.NotNull;

public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        HelpCrunch.initialize(
                "mobile",
                4845,
                "9VMlXLZtos8iGcoZ1oX+qgcMzAUb7S/HZVKnPdQXDtaub4rlQPom7ajMK5+10FoHGSMwGhDI/9xE+zVzQ482VA=="
        );
    }
}
