# Third-party libraries
```kotlin
allprojects {
    repositories {
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
```kotlin
// AndroidX
api("com.google.android.material:material:1.12.0")
api("androidx.appcompat:appcompat:1.7.0")
api("androidx.recyclerview:recyclerview:1.3.2")
api("androidx.lifecycle:lifecycle-process:2.6.2")
api("androidx.activity:activity-ktx:1.8.0")
api("androidx.fragment:fragment-ktx:1.6.1")
// Firebase
api(platform("com.google.firebase:firebase-bom:33.10.0"))
api("com.google.firebase:firebase-messaging-ktx")
// Gson
api("com.google.code.gson:gson:2.11.0")
// Retrofit
api("com.squareup.retrofit2:retrofit:2.11.0")
api("com.squareup.retrofit2:converter-gson:2.11.0")
api(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
api("com.squareup.okhttp3:okhttp")
api("com.squareup.okhttp3:logging-interceptor")
api("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
// Room
api("androidx.room:room-runtime:2.6.1")
ksp("androidx.room:room-compiler:2.6.1")
api("androidx.room:room-ktx:2.6.1")
// Coil
api("io.coil-kt:coil:2.7.0")
api("io.coil-kt:coil-gif:2.7.0")
api("io.coil-kt:coil-svg:2.7.0")
// Markdown
api("io.noties.markwon:core:4.6.2")
api("io.noties.markwon:html:4.6.2")
api("io.noties.markwon:inline-parser:4.6.2")
api("io.noties.markwon:image:4.6.2")
api("io.noties.markwon:linkify:4.6.2")
// Worker
api("androidx.work:work-runtime-ktx:2.9.0")
// LiveData
api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
api("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
// Koin
api("io.insert-koin:koin-android:4.0.0")
//Video
api("com.github.TalbotGooday:Android-Oembed-Video:0.2.8")
//Images
api("com.github.chrisbanes:PhotoView:2.3.0")
//Drawable
api("com.github.duanhong169:drawabletoolbox:1.0.7")
api("com.github.TalbotGooday:AvatarView:0.1.4")
// Socket
api("io.socket:socket.io-client:1.0.1")
// Coroutines
api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
```
