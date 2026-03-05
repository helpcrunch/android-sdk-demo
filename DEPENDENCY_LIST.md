# Third-party libraries

```kotlin
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven {
            url = uri("https://jitpack.io")
        }
        maven {
            url = uri("https://dl.bintray.com/kotlin/dokka")
        }
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
}
```

----

```toml
[versions]
agp = "8.13.2"
kotlin = "2.3.0"
ksp = "2.2.20-2.0.3"
activity-ktx = "1.12.3"
android-documentation-plugin = "2.1.0"
android-oembed-video = "0.2.9"
appcompat = "1.7.1"
avatarview = "0.1.4"
coil = "3.4.0"
converter-gson = "3.0.0"
markwon = "4.6.2"
drawable-toolbox = "1.0.7"
firebase-bom = "34.10.0"
fragment-ktx = "1.8.9"
gson = "2.13.2"
koin-bom = "4.1.1"
dokka = "2.1.0"
google-services = "4.4.4"
crashlytics = "3.0.6"
kotlinx-coroutines-android = "1.10.2"
jetbrains-kotlin-jvm = "2.3.0"
lifecycle-livedata-ktx = "2.10.0"
lifecycle-process = "2.10.0"
lifecycle-viewmodel-ktx = "2.10.0"
material = "1.13.0"
okhttp-bom = "5.3.2"
photoview = "2.3.0"
recyclerview = "1.4.0"
retrofit = "3.0.0"
retrofit2-kotlin-coroutines-adapter = "0.9.2"
room-ktx = "2.8.4"
room-runtime = "2.8.4"
room-compiler = "2.8.4"
socket-io-client = "1.0.1" # strictly
work-runtime-ktx = "2.11.1"
broadcast-manager = "1.1.0"

[plugins]
androidApplication = { id = "com.android.application", version.ref = "agp" }
androidLibrary = { id = "com.android.library", version.ref = "agp" }
kotlinAndroid = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
dokka = { id = "org.jetbrains.dokka", version.ref = "dokka" }
googleServices = { id = "com.google.gms.google-services", version.ref = "google-services" }
crashlytics = { id = "com.google.firebase.crashlytics", version.ref = "crashlytics" }
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
jetbrainsKotlinJvm = { id = "org.jetbrains.kotlin.jvm", version.ref = "jetbrains-kotlin-jvm" }

[libraries]
activity-ktx = { module = "androidx.activity:activity-ktx", version.ref = "activity-ktx" }
android-documentation = { module = "org.jetbrains.dokka:android-documentation-plugin", version.ref = "android-documentation-plugin" }
android-oembed-video = { module = "com.github.TalbotGooday:Android-Oembed-Video", version.ref = "android-oembed-video" }
appcompat = { module = "androidx.appcompat:appcompat", version.ref = "appcompat" }
avatarview = { module = "com.github.TalbotGooday:AvatarView", version.ref = "avatarview" }
coil-svg = { module = "io.coil-kt.coil3:coil-svg", version.ref = "coil" }
coil-gif = { module = "io.coil-kt.coil3:coil-gif", version.ref = "coil" }
coil-network-okhttp = { module = "io.coil-kt.coil3:coil-network-okhttp", version.ref = "coil" }
coil = { module = "io.coil-kt.coil3:coil", version.ref = "coil" }
converter-gson = { module = "com.squareup.retrofit2:converter-gson", version.ref = "converter-gson" }
drawabletoolbox = { module = "com.github.duanhong169:drawabletoolbox", version.ref = "drawable-toolbox" }
firebase-bom = { module = "com.google.firebase:firebase-bom", version.ref = "firebase-bom" }
firebase-messaging = { module = "com.google.firebase:firebase-messaging" }
fragment-ktx = { module = "androidx.fragment:fragment-ktx", version.ref = "fragment-ktx" }
gson = { module = "com.google.code.gson:gson", version.ref = "gson" }
koin-bom = { module = "io.insert-koin:koin-bom", version.ref = "koin-bom" }
koin-android = { module = "io.insert-koin:koin-android" }
kotlinx-coroutines-android = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-android", version.ref = "kotlinx-coroutines-android" }
lifecycle-livedata-ktx = { module = "androidx.lifecycle:lifecycle-livedata-ktx", version.ref = "lifecycle-livedata-ktx" }
lifecycle-viewmodel-ktx = { module = "androidx.lifecycle:lifecycle-viewmodel-ktx", version.ref = "lifecycle-viewmodel-ktx" }
markwon-core = { module = "io.noties.markwon:core", version.ref = "markwon" }
markwon-html = { module = "io.noties.markwon:html", version.ref = "markwon" }
markwon-image = { module = "io.noties.markwon:image", version.ref = "markwon" }
markwon-linkify = { module = "io.noties.markwon:linkify", version.ref = "markwon" }
markwon-inline-parser = { module = "io.noties.markwon:inline-parser", version.ref = "markwon" }
lifecycle-process = { module = "androidx.lifecycle:lifecycle-process", version.ref = "lifecycle-process" }
material = { module = "com.google.android.material:material", version.ref = "material" }
okhttp = { module = "com.squareup.okhttp3:okhttp" }
okhttp-bom = { module = "com.squareup.okhttp3:okhttp-bom", version.ref = "okhttp-bom" }
photoview = { module = "com.github.chrisbanes:PhotoView", version.ref = "photoview" }
recyclerview = { module = "androidx.recyclerview:recyclerview", version.ref = "recyclerview" }
retrofit = { module = "com.squareup.retrofit2:retrofit", version.ref = "retrofit" }
retrofit2-kotlin-coroutines-adapter = { module = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter", version.ref = "retrofit2-kotlin-coroutines-adapter" }
room-compiler = { module = "androidx.room:room-compiler", version.ref = "room-compiler" }
room-ktx = { module = "androidx.room:room-ktx", version.ref = "room-ktx" }
room-runtime = { module = "androidx.room:room-runtime", version.ref = "room-runtime" }
socket-io-client = { module = "io.socket:socket.io-client", version.ref = "socket-io-client" }
work-runtime-ktx = { module = "androidx.work:work-runtime-ktx", version.ref = "work-runtime-ktx" }
broadcastManager = { module = "androidx.localbroadcastmanager:localbroadcastmanager", version.ref = "broadcast-manager" }
```
