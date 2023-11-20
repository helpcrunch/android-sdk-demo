package com.helpcrunch.demo.application

import androidx.multidex.MultiDexApplication
import com.helpcrunch.library.core.HelpCrunch

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

//        HelpCrunch.initialize(
//            organization = "organization",
//            appId = 1000,
//            secret = "secret"
//        )
        HelpCrunch.initialize(
            "mobile",
            2889,
            "BT4na/0/fHk6d1jtg0qKiK5GoxXf1/GgP0ay0ps2UiWJPfdPeUDFUYwnIjBFO49oilOKx+EMg2Tw+BJsS/hI6g=="
        );
    }
}
