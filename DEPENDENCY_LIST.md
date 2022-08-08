# Third-party libraries
```groovy
allprojects {
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
        google()
    }
}
```
----
```groovy
// AndroidX
api "com.google.android.material:material:1.5.0"
api "androidx.appcompat:appcompat:1.2.0"
api "androidx.core:core-ktx:1.3.2"
api "androidx.recyclerview:recyclerview:1.2.1"

// Firebase
api platform("com.google.firebase:firebase-bom:30.2.0")
api "com.google.firebase:firebase-messaging"

// Gson
api "com.google.code.gson:gson:2.9.0"

// Retrofit
api "com.squareup.retrofit2:retrofit:2.9.0"
api "com.squareup.retrofit2:converter-gson:2.9.0"
api("com.squareup.okhttp3:okhttp:3.12.13")
api("com.squareup.okhttp3:logging-interceptor:3.12.13")
api "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter
api("com.localebro:okhttpprofiler:1.0.8")

// Room
api "androidx.room:room-runtime:2.4.2"
kapt "androidx.room:room-compiler:2.4.2"
api "androidx.room:room-ktx:2.4.2"

// Emojify
api "androidx.emoji:emoji:1.1.0"
api "androidx.emoji:emoji-appcompat:1.1.0"

// Coil
api("io.coil-kt:coil:1.4.0")
api "io.coil-kt:coil-gif:1.4.0"
api "io.coil-kt:coil-svg:1.4.0"

// Markdown
api("io.noties.markwon:core:4.6.2")
api("io.noties.markwon:html:4.6.2")
api("io.noties.markwon:inline-parser:4.6.2")
api("io.noties.markwon:image:4.6.2")

// Worker
api 'androidx.work:work-runtime-ktx:2.7.1'

// LiveData
api "androidx.lifecycle:lifecycle-extensions:2.2.0"
api "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
api "androidx.lifecycle:lifecycle-livedata-ktx:2.4.0"

// Koin
api "io.insert-koin:koin-android:3.1.5"

// Video
api("com.github.TalbotGooday:Android-Oembed-Video:0.2.1") {
    exclude group: "androidx.appcompat", module: "appcompat"
    exclude group: "com.squareup.okhttp3", module: "okhttp"
}

// Images
api "com.github.chrisbanes:PhotoView:2.3.0"

// Drawable
api "com.github.duanhong169:drawabletoolbox:1.0.7"
api "com.github.TalbotGooday:AvatarView:0.1.1"

// Socket
api("io.socket:socket.io-client:1.0.0")

// Coroutines
api "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2"
```
