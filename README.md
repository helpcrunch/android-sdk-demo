<a href="https://helpcrunch.com/"><p align="center"><img alt="flow" width="600" src="https://helpcrunch.com/img/layout/menu/logo.svg"></p></a>

<p align=center>
<a href="https://maven-badges.herokuapp.com/maven-central/com.helpcrunch/chat-sdk"><img alt="Maven Central" src="https://maven-badges.herokuapp.com/maven-central/com.helpcrunch/chat-sdk/badge.svg" /></a>
<img alt="Platform" src="https://img.shields.io/badge/platforms-Android-green.svg" />
<img alt="Languages" src="https://img.shields.io/badge/languages-Kotlin-F18E33.svg" />
<a href="https://www.apache.org/licenses/LICENSE-2.0.html"><img alt="Apache License" src="http://img.shields.io/badge/license-APACHE2-blue.svg?style=flat" /></a>
</p>

### Helpcrunch Android SDK Demo

A perfect live chat, email automation and a super-intuitive help desk in one smart customer
communication platform.

This SDK can be added to **Android**, **Flutter**, **Apache Cordova** or **React Native** projects
to start a live chat from a mobile application.

## Changelog

The changelog is available [here](CHANGELOG.md)

## Contents

- [Requirements](#requirements)
- [Gradle](#gradle)
- [Demo Installation](#demo-installation)
- [Firebase Setup](#firebase-setup-for-push-notifications)
- [Simple Flow](#simple-flow)
- [Troubleshooting](#troubleshooting)
- [R8 / Proguard](#r8--proguard)
- [Documentation](#documentation)
- [License](#license)

## Gradle

Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
    repositories {
        //...
        mavenCentral()
        maven { url 'https://jitpack.io' }
        // For snapshot versions (optional)
        maven {
            name = "Central Portal Snapshots"
            url = "https://central.sonatype.com/repository/maven-snapshots/"
        }
    }
}
```

Add the dependency

```gradle
implementation 'com.helpcrunch:chat-sdk:3.x.x'
```

This library uses Java 11 bytecode, so you will need to enable it in your project as well:

```gradle
android {
    //...
    // For Java projects
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_11
        targetCompatibility JavaVersion.VERSION_11
    }
    // For Kotlin projects
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }
}
```

**Note:** For Kotlin projects, you'll also need to add this import at the top of your
`build.gradle`:

```gradle
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
```

## Requirements

- Java 11/Kotlin
- Min Android API - Android 6.0 Marshmallow (API 23)

## Demo Installation

1. Clone or download repository
2. Open the repository in Android Studio
3. Wait until the end of the Gradle generation (indication in the status bar below)
4. Add `google-services.json` file to the `../app/` folder (optional, only if you want to have push
   notifications)
5. On the toolbar, click **Build → Generate Signed APK**
6. Select a signature key or create a new one in the "Key store path"
7. Fill in the password fields and the alias field according to the data in the **"key data"** file
   and click **"Next"**
8. Select both items in the "Signature Versions" field.
9. Click **"Finish"**

## Firebase Setup (for Push Notifications)

To enable push notifications, you need to:

1. Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
2. Add your Android app to the Firebase project
3. Download the `google-services.json` file
4. Place it in the `app/` folder of your project
5. Add Firebase dependencies (already included in the SDK)
6. Configure your Firebase Cloud Messaging (FCM) server key in HelpCrunch dashboard

## Simple flow:

A. Initialize HelpCrunch by calling the following in the `onCreate()` method of your application

```kotlin
 HelpCrunch.initialize(ORGANISATION, APP_ID, SECRET)
```

also you can set up option or user data

```kotlin
 val user = HCUser.Builder()
    .withEmail("test@user.data")
    .withName("Test User")
    .build()

val options = HCOptions.Builder()
    .setTheme(/*some HCTheme*/)
    .build()

HelpCrunch.initialize(ORGANIZATION, APP_ID, SECRET, user, options)
```

B. If you want to update customer info - just call

```kotlin
HelpCrunch.updateUser(user)
```

user data could be created like this:

```kotlin
val user = HCUser.Builder()
    .withName("username")
    .withEmail("email")
    .withPhone("phone")
    .withUserId("registerUserId")
    .withCustomData(/*some Map<String, Any>*/)
    .withCompany("organization")
    .build()
```

All fields are optional. Custom data could be created like this:

```kotlin
val customData = hashMapOf<String, Any>()
customData.put("CUSTOM_TIME", System.currentTimeMillis())
```

C. Open Chat Screen calling `showChatScreen(Context context)` method:

```kotlin
HelpCrunch.showChatScreen(context)
```

## Troubleshooting

### Duplicate META-INF files error

If you encounter an error about duplicate `META-INF/versions/9/OSGI-INF/MANIFEST.MF` files during
build, add the following to your app's `build.gradle`:

```gradle
android {
    //...
    packaging {
        resources {
            excludes += ['META-INF/versions/9/OSGI-INF/MANIFEST.MF']
        }
    }
}
```

## R8 / Proguard

HelpCrunch SDK is fully compatible with R8 out of the box and doesn't require adding any extra
rules.

## Documentation

The documentation is available in our Knowledge Base located
at [this link](https://docs.helpcrunch.com/android-sdk).

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details
