plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.horoscapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.horoscapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        /*
        jvmTarget = "11"
         */
    }
    /**
     * Esto habilita el viewBinding, es una forma de evitar findViewById(R.id.texto)
     */
    buildFeatures {
        viewBinding = rootProject.extra["viewBindingEnabled"] as Boolean
    }
}

dependencies {
//    val navVersion="2.7.1"
    val navVersion="2.8.9"
    val hilt_android="2.51.1" //2.48
    val hilt_navigation_compose="1.0.0"

    //NavComponent
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //DaggerHilt
//    implementation(libs.hilt.android)
//    kapt(libs.hilt.compiler)
    implementation("com.google.dagger:hilt-android:$hilt_android")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_android")

    // Por defecto
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

//katp {
//    correctErrorTypes = true
//}