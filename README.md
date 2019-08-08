![](https://helpcrunch.com/img/layout/menu/logo.svg)

### Helpcrunch Android SDK Demo 
This SDK can be added to **Android**, **Flutter** or **React Native** projects to start a live chat from a mobile application.

#### Maven repository:
https://mvnrepository.com/artifact/com.helpcrunch/helpcrunch-sdk

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.helpcrunch/helpcrunch-sdk/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.helpcrunch/helpcrunch-sdk)


### Prerequisites
Java 8, Kotlin

### Installation
1. Clone or download repository
2. Open the repository in Android Studio
3. Wait until the end of the Gradle generation (indication in the status bar below)
4. On the toolbar, click **Build -> Generate Signed APK**
5. Select a signature key or create a new one in the "Key store path"
6. Fill in the password fields and the alias field according to the data in the **"key data"** file and click **"Next"**
7. Select both items in the "Signature Versions" field.
8. Click **"Finish"**

### Simple flow: ###

A. Import library in aar format manually into your project

B. Initialize HelpCrunch by calling the following in the oncreate() method of your application 
```

        HelpCrunch.initialize(AppContext, ORGANISATION, APPID, APPSECRET, PUSHID);

```

also you can set up option if user name is mandatory


```
        HelpCrunch.Options opts = new HelpCrunch.Options();
        opts.requestName = true;
        HelpCrunch.initializeWithOptions(this, ORGANISATION, APP_ID, SECRET, PUSH_ID, opts);
```

C. If you want to update customer info - just call

```
            HelpCrunch.updateUser(c);
```

user data could be created like this:

```
                  User c = new UserBuilder()
                    .withName(mNameEdit.getText().toString())
                    .withUserID(mIdEdit.getText().toString())
                    .withEmail(mEmailEdit.getText().toString())
                    .withCustomData(custom)
                    .build();
```

All fields are optional. Custom data could be created like this:

```
        HashMap custom = new HashMap();
        custom.put("CUSTOM_TIME", System.currentTimeMillis());
```
