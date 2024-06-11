plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.kemakolam.republicserviceapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kemakolam.republicserviceapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        correctErrorTypes = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }



}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    //--------------API Dependencies------------------
    // Retrofit -> Network
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    //GSON
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.10.0")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation ("androidx.compose.runtime:runtime-livedata:1.4.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0") //enables the hiltViewModel() in compose



    //---------------------Room database dependencies------------------
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")


    //-----------------Image Rendering dependency--------------
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //------------------Navigation Dependency --------------------
    implementation ("androidx.navigation:navigation-compose:2.7.7")
    implementation ("androidx.compose.ui:ui:1.0.5")
    implementation ("androidx.compose.material3:material3:1.0.0-alpha08")

    //---------------------Animation--------------------------------
    implementation ("com.google.accompanist:accompanist-navigation-animation:0.34.0")

    //----------------Testing Dependencies----------------
    testImplementation ("org.mockito:mockito-core:3.12.4") // Mockito core
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0-RC")

    testImplementation ("org.mockito:mockito-inline:3.11.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
    testImplementation ("androidx.arch.core:core-testing:2.1.0")

    testImplementation ("androidx.test:core:1.4.0")
    androidTestImplementation ("androidx.room:room-testing:2.5.0")
    androidTestImplementation ("androidx.test:core-ktx:1.4.0")


}