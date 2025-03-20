// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false

    // Navegación segura SafeArgs
    // En el vídeo usa la 2.7.1
    id("androidx.navigation.safeargs.kotlin") version "2.8.9" apply false
}
val viewBindingEnabled by extra(true)
