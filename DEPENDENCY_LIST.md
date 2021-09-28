# Third-party libraries

```groovy
// AndroidX
implementation 'com.google.android.material:material:1.3.0'
implementation 'androidx.appcompat:appcompat:1.2.0'
implementation 'androidx.core:core-ktx:1.3.2'
implementation 'androidx.recyclerview:recyclerview:1.1.0'

// Firebase
implementation platform('com.google.firebase:firebase-bom:28.4.1')
implementation 'com.google.firebase:firebase-messaging'

// Gson
implementation 'com.google.code.gson:gson:2.8.6'

// Retrofit
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
api("com.squareup.okhttp3:okhttp:3.12.13")
api("com.squareup.okhttp3:logging-interceptor:3.12.13")
implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
implementation('com.localebro:okhttpprofiler:1.0.8')

// Room
implementation "androidx.room:room-runtime:2.3.0"
kapt "androidx.room:room-compiler:2.3.0"
implementation "androidx.room:room-ktx:2.3.0"

// Emojify
implementation 'androidx.emoji:emoji:1.1.0'
implementation 'androidx.emoji:emoji-appcompat:1.1.0'

// Coil
implementation("io.coil-kt:coil:1.2.1")
implementation "io.coil-kt:coil-gif:1.2.1"
implementation "io.coil-kt:coil-svg:1.2.1"

// Socket
implementation "io.socket:engine.io-client:1.0.0"

// Markdown
implementation("io.noties.markwon:core:4.6.2")
implementation("io.noties.markwon:html:4.6.2")
implementation("io.noties.markwon:inline-parser:4.6.2")
implementation("io.noties.markwon:image:4.6.2")

// Worker
implementation "androidx.work:work-runtime-ktx:2.5.0"

// LiveData
implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"

// Koin
implementation "io.insert-koin:koin-android:3.1.2"

// Video
implementation('com.github.TalbotGooday:Android-Oembed-Video:0.2.1') {
    exclude group: 'androidx.appcompat', module: 'appcompat'
    exclude group: 'com.squareup.okhttp3', module: 'okhttp'
}

// Images
implementation 'com.github.chrisbanes:PhotoView:2.3.0'

// Drawable
implementation 'com.github.duanhong169:drawabletoolbox:1.0.7'
implementation 'com.github.TalbotGooday:AvatarView:0.0.5'

// Socket IO
implementation('io.socket:socket.io-client:1.0.0')

// Coroutines
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2'
```
