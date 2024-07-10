 <a href="https://helpcrunch.com/"><p align="center"><img alt="flow" width="600" src="https://helpcrunch.com/img/layout/menu/logo.svg"></p></a>

<p align=center>
<a href="https://maven-badges.herokuapp.com/maven-central/com.helpcrunch/chat-sdk"><img alt="Maven Central" src="https://maven-badges.herokuapp.com/maven-central/com.helpcrunch/chat-sdk/badge.svg" /></a>
<img alt="Platform" src="https://img.shields.io/badge/platforms-Android-green.svg" />
<img alt="Languages" src="https://img.shields.io/badge/languages-Kotlin-F18E33.svg" />
<a href="https://www.apache.org/licenses/LICENSE-2.0.html"><img alt="Apache License" src="http://img.shields.io/badge/license-APACHE2-blue.svg?style=flat" /></a>
</p>

### Helpcrunch Android SDK Demo
A perfect live chat, email automation and a super-intuitive help desk in one smart customer communication platform.

This SDK can be added to **Android**, **Flutter**, **Apache Cordova** or **React Native** projects to start a live chat from a mobile application.

## Changelog
The changelog is available [here](CHANGELOG.md)

## Contents

- [Requirements](#requirements)
- [Gradle](#gradle)
- [Demo Installation](#demo-installation)
- [Simple Flow](#simple-flow)
- [R8 / Proguard](#r8--proguard)
- [Documentation](#documentation)
- [License](#license)
    
##  Gradle
Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency
```groovy
implementation 'com.helpcrunch:chat-sdk:3.x.x'
```

This library uses [Java 8 bytecode](https://developer.android.com/studio/write/java8-support), so you will need to enable it in your project as well:

```gradle
android {
    //...
    // For Java projects
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    // For Kotlin projects
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}
```

## Requirements
 - Java 8/Kotlin
 - Min Android API - Android 5.0 KitKat (API 21)

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
A. Initialize HelpCrunch by calling the following in the onCreate() method of your application
```java
 HelpCrunch.initialize(ORGANISATION, APP_ID, SECRET);
```
also you can set up option or user data
```java
 HCUser user = new HCUser.Builder()
	.withEmail("test@user.data")
	.withName("Test User")
	.build();

HCOptions.Builder options = new HCOptions.Builder()
	.setTheme(/*some HCTheme*/)
	.build();

HelpCrunch.initialize(ORGANIZATION, APP_ID, SECRET, user, options);
```
B. If you want to update customer info - just call
```java
HelpCrunch.updateUser(user);
```
user data could be created like this:
```java
HCUser user = new HCUser.Builder()
	.withName("username")
	.withEmail("email")
	.withPhone("phone")
	.withUserId("registerUserId")
	.withCustomData(/*some Map<String, Object>*/)
	.withCompany("organization")
	.build();
```
All fields are optional. Custom data could be created like this:
```java
HashMap<String, Object> customData = new HashMap();
customData.put("CUSTOM_TIME", System.currentTimeMillis());
```
C. Open Chat Screen calling `showChatScreen(Context context)` method:
```java
Helpcrunch.showChatScreen(context);
```
## R8 / Proguard
HelpCrunch SDK is fully compatible with R8 out of the box and doesn't require adding any extra rules.

## Documentation

The documentation is available in our Knowledge Base located at [this link](https://docs.helpcrunch.com/android-sdk).

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details
