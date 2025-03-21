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
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL",  "\"https://newastro.vercel.app/\"")
            resValue("string","fmmName", "HoroscApp")
        }
        getByName("debug") {
            isDebuggable = true
            buildConfigField("String", "BASE_URL",  "\"https://newastro.vercel.app/\"")
            resValue("string","fmmName", "[DEBUG] HoroscAppDebug")
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
        buildConfig=true
    }
}

dependencies {
//    val navVersion="2.7.1"
//    val navVersion="2.8.9"

    //NavComponent
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //DaggerHilt
//    implementation(libs.hilt.android)
//    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //Retrofit -> Para Rest
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

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