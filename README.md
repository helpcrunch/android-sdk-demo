
![HelpCrunch](https://helpcrunch.com/img/layout/menu/logo.svg)

### Helpcrunch Android SDK Demo
A perfect live chat, email automation and a super-intuitive help desk in one smart customer communication platform.

This SDK can be added to **Android**, **Flutter** or **React Native** projects to start a live chat from a mobile application.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.helpcrunch/sdk/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.helpcrunch/sdk) [![Platform](https://img.shields.io/badge/platforms-Android-green.svg)]() [![Languages](https://img.shields.io/badge/languages-Kotlin-F18E33.svg)]()  [![Apache License](http://img.shields.io/badge/license-APACHE2-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0.html)
## Contents

- [Requirements](#requirements)
- [Maven repository](#maven)
- [Migration Guide](#migration-guide)
- [Demo Installation](#demo-installation)
- [Simple Flow](#simple-flow)
- [Documentation](#documentation)
- [License](#license)

##  Maven
    implementation 'com.helpcrunch:sdk:2.0.0-beta11'

## Requirements
 - Java 8/Kotlin
 - Min Android SDK >= 19

## Migration guide

If your application currently uses v1.x SDK it is important that you migrate to Android SDK v2.x so that your application will get newest important features/ You can update update your App with our [migration giude](https://helpcrunch.helpcrunch.com/knowledge-base/articles/245).

## Demo Installation
1. Clone or download repository
2. Open the repository in Android Studio
3. Wait until the end of the Gradle generation (indication in the status bar below)
4. Add `google-services.json` file to the `../app/` folder
5. On the toolbar, click **Build -> Generate Signed APK**
6. Select a signature key or create a new one in the "Key store path"
7. Fill in the password fields and the alias field according to the data in the **"key data"** file and click **"Next"**
8. Select both items in the "Signature Versions" field.
9. Click **"Finish"**

## Simple flow:
A. Initialize HelpCrunch by calling the following in the oncreate() method of your application

    HelpCrunch.initialize(this, ORGANISATION, APP_ID, SECRET)

also you can set up option or user data

    HCUser user = new HCUser.Builder()
            .withEmail("test@user.data")
            .withName("Test User")
            .build();

    HCOptions.Builder options = new HCOptions.Builder()
	        .setTheme(theme)
	        .build();

    HelpCrunch.initialize(this, ORGANISATION, APP_ID, SECRET, user, options)

B. If you want to update customer info - just call

      HelpCrunch.updateUser(user);

user data could be created like this:

    HCUser user = new HCUser.Builder()
            .withName("username")
            .withEmail("email")
            .withPhone("phone")
            .withUserId("registerUserId")
            .withCustomData(customData)
            .withCompany("organization")
            .build();

All fields are optional. Custom data could be created like this:

     HashMap customData = new HashMap();
     customData.put("CUSTOM_TIME", System.currentTimeMillis());
C. Open Chat Screen calling `showChatScreen(Context context)` method:

    Helpcrunch.showChatScreen(Context context)

## Pro Guard

    -keep class com.helpcrunch.** { *; }

## Documentation

The documentation is available in our Knowledge Base located at [this link](https://docs.helpcrunch.com/android-sdk).

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details
