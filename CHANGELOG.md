# Changelog

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
