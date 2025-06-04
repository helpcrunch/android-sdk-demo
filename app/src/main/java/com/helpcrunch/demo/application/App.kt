package com.helpcrunch.demo.application

import androidx.multidex.MultiDexApplication
import com.helpcrunch.library.core.HelpCrunch

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        HelpCrunch.initialize(
            "qaleks",
            76,
            "bhVe1W3dbOMjrqvWuQf/W1gzvJepuo0EAudlwcn6sCwDxv4nZQSqO1zI7dkrPOJpD0HpjYtrWqyfAFdsV8lz+g=="
        );
    }
}
