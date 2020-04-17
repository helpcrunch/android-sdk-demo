 <a href="https://helpcrunch.com/"><img src="https://helpcrunch.com/img/layout/menu/logo.svg" width="400" height="90" alt="HelpCrunch"></a>

### Helpcrunch Android SDK Demo
A perfect live chat, email automation and a super-intuitive help desk in one smart customer communication platform.

This SDK can be added to **Android**, **Flutter** or **React Native** projects to start a live chat from a mobile application.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.helpcrunch/sdk/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.helpcrunch/sdk) [![Platform](https://img.shields.io/badge/platforms-Android-green.svg)]() [![Languages](https://img.shields.io/badge/languages-Kotlin-F18E33.svg)]()  [![Apache License](http://img.shields.io/badge/license-APACHE2-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0.html)

## Sample Apk
[Download](https://github.com/helpcrunch/android-sdk-demo/blob/master/app/release/app-release.apk?raw=true) sample apk

## Contents

- [Requirements](#requirements)
- [Gradle](#gradle)
- [Migration Guide](#migration-guide)
- [Demo Installation](#demo-installation)
- [Simple Flow](#simple-flow)
- [SVG support](#svg-support)
- [Pro Guard](#pro-guard)
- [Documentation](#documentation)
- [License](#license)

##  Gradle
Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency
```groovy
implementation 'com.helpcrunch:sdk:2.0.x'
```
## Requirements
 - Java 8/Kotlin
 - Min Android API - Android 4.4 KitKat (API 19)

## Migration guide

If your application currently uses v1.x SDK it is important that you migrate to Android SDK v2.x so that your application will get newest important features. You can update update your App with our [migration giude](https://docs.helpcrunch.com/android-sdk/android-sdk2-migration-guide).

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
 HelpCrunch.initialize(ORGANISATION, APP_ID, SECRET)
```
also you can set up option or user data
```java
 HCUser user = new HCUser.Builder()
	.withEmail("test@user.data")
	.withName("Test User")
	.build();

HCOptions.Builder options = new HCOptions.Builder()
	.setTheme(theme)
	.build();

HelpCrunch.initialize(ORGANISATION, APP_ID, SECRET, user, options);
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
	.withCustomData(customData)
	.withCompany("organization")
	.build();
```
All fields are optional. Custom data could be created like this:
```java
HashMap<String, Object> customData = new HashMap();
customData.put("CUSTOM_TIME", System.currentTimeMillis());
```
C. Open Chat Screen calling `showChatScreen(context)` method:
```java
Helpcrunch.showChatScreen(context)
```
## SVG support 
If you want to support SVG images in the chat you just need to generate Glide API. 
More information about Glide's generated API you can find at [this link](https://bumptech.github.io/glide/doc/generatedapi.html "link").
## Pro Guard
```java
-keep class com.helpcrunch.* { *; }
```
## Documentation

The documentation is available in our Knowledge Base located at [this link](https://docs.helpcrunch.com/android-sdk).

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details
