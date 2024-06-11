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
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)

    //OkHttp
    implementation(libs.okhttp)

    //GSON
    implementation(libs.gson)
    implementation(libs.converter.gson)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.runtime.livedata)
    implementation (libs.androidx.hilt.navigation.compose) //enables the hiltViewModel() in compose



    //---------------------Room database dependencies------------------
    val room_version = "2.6.1"
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)


    //-----------------Image Rendering dependency--------------
    implementation(libs.glide)

    //------------------Navigation Dependency --------------------
    implementation (libs.androidx.navigation.compose)
    implementation (libs.ui)
    implementation (libs.material3)

    //---------------------Animation--------------------------------
    implementation (libs.accompanist.navigation.animation)

    //----------------Testing Dependencies----------------
    testImplementation (libs.mockito.core) // Mockito core
    testImplementation (libs.kotlinx.coroutines.test)

    testImplementation (libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
    testImplementation (libs.androidx.core.testing)

    testImplementation (libs.androidx.core)
    androidTestImplementation (libs.androidx.room.testing)
    androidTestImplementation (libs.core.ktx)


}