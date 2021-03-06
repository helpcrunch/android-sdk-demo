# Changelog

## v3.1.4 (11/06/2021)
* Added the ability to display the knowledge base inside the SDK

#### Bug Fixes:
- [#44](https://github.com/helpcrunch/android-sdk-demo/issues/44) CLEARTEXT communication to ucarecdn.com not permitted by network security policy
- [#43](https://github.com/helpcrunch/android-sdk-demo/issues/43) java.io.IOException: TOO_MANY_REGISTRATIONS
- [#39](https://github.com/helpcrunch/android-sdk-demo/issues/39) OutOfMemoryError
- [#48](https://github.com/helpcrunch/android-sdk-demo/issues/48) NullPointerException: view.findViewById(R.id.hc_progress_indicator) must not be null
- [#47](https://github.com/helpcrunch/android-sdk-demo/issues/47) IllegalStateException: FragmentManager is already executing transactions
- [#35](https://github.com/helpcrunch/android-sdk-demo/issues/35) IOException: SERVICE_NOT_AVAILABLE | AUTHENTICATION_FAILED | MISSING_INSTANCEID_SERVICE
---

## v3.1.2 - v.3.1.3 (12/05/2021)
* Bug fixes and improvements
* Fixed compatibility with OkHttp 4.x versions
---

## v3.0.9 - v.3.1.1 (04/05/2021)
* Fab can now show the number of new incoming messages
* The fab color will now depend on the main color if a custom theme is used and the fab settings are not added
* The built-in file picker has been removed for all android versions.
* Downloaded files are now saved to the public Downloads folder

#### Bug Fixes:
- Fixed looping of automatic scroll on new agent message
- Fixed the initialization bug on Android 6.0
- [#41](https://github.com/helpcrunch/android-sdk-demo/issues/41) Fatal Exception: com.google.android.gms.tasks.RuntimeExecutionException
---

## v3.0.8 (17/04/2021)
* Added functionality for previewing articles from the knowledge base
* Support for sending files on Android 11
* Pre chat form redesign. Pre chat form can now be changed on the fly using the Android application settings in our web-console
* Notifications changed. Added support for response with notification


#### Bug Fixes:
- [#33](https://github.com/helpcrunch/android-sdk-demo/issues/33) Duplicate class a.a.a.a found in modules
- [#31](https://github.com/helpcrunch/android-sdk-demo/issues/31) NPE: Attempt to invoke virtual method 'void androidx.recyclerview.widget.RecyclerView.o0(int)'
- [#26](https://github.com/helpcrunch/android-sdk-demo/issues/26) OutOfMemoryErrors in 3.0.8-alpha.02
- [#28](https://github.com/helpcrunch/android-sdk-demo/issues/28) ConcurrentModificationException
- [#25](https://github.com/helpcrunch/android-sdk-demo/issues/25) Can only download HTTP/HTTPS URIs
---

## v3.0.7 (09/11/2020)
* More stable for the Xamarin apps
* Images and markdown support for broadcast messages
* Now a message longer than 5000 characters is split into several messages
* Bug fixes and improvements
---

## v3.0.6 (24/09/2020)
Bug fixes and improvements
* More stable for the Cordova apps
* Fixed crashes after excluding EmojiCompat
* Fixed crashes with Firebase push-notifications when app is terminated
* Optimizing memory usage
---

## v3.0.1 (31/08/2020)
* Migration to a new service
* Bug fixes and other improvements
---

## v2.0.5 (17/03/2020)
* Fixed bug with pre-chat form user data
* Small code improvements
---

## v2.0.3 (31/03/2020)
Bug fixes and other improvements
---

## v2.0.2 (18/03/2020)
* Added https://github.com/helpcrunch/android-sdk-demo/issues/11
* OkHttpProfiler removed from dependencies
* Enhanced user event tracking. Now you can send more data in the `Map<String, Object>`
* Bug fixes and other improvements
---

## v2.0.1 (27/02/2020)
* Fixed Bug with wrong chat theme when launch from notifications
* Fixed https://github.com/helpcrunch/android-sdk-demo/issues/12
* Download and Upload services replaced with `Workers`
* Migraded from `android.arch.work` to `androidx.work`
* Now HelpCrunch starts up automatically using a `ContentProvider` defined in the HelpCrunch SDK. There is no longer any need to pass the application to the `initialize` function
---

## v2.0.0-rc.5 (11/02/2020)
* Bug with server time fixed
* Video parsing fixed
* Fixed tabs crash with different AndroidX appcompat versions
* Added the ability to disable the display of emoji and SVG images to reduce the size of the application
* Added image keyboard support
---

## v2.0.0-rc.3 (24/01/2020)
* Tablet support
* Image keyboard support
* SVG images support. [More...](https://github.com/helpcrunch/android-sdk-demo#svg-support)
* Hidden agents are no longer displayed
* Video support added in complex messages
* Bug fixes and other improvements
---

## v2.0.0-beta19 (13/11/2019)
* Improved the ability to update the user using the method. The user immediately appears online and is added to contacts
* Bug fixes and other improvements
---
