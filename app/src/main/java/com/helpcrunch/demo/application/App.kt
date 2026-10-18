package com.helpcrunch.demo.application

import androidx.multidex.MultiDexApplication
import com.helpcrunch.library.core.HelpCrunch

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        HelpCrunch.initialize(
            organization = "",
            appId = 0,
            secret = ""
        );
    }
}
