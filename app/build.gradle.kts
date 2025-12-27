plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
}

// Use old-style apply so the plugin from `buildscript` is picked up
apply(plugin = "com.google.dagger.hilt.android")

android {
    namespace = "com.example.jetnotes"
    compileSdk = 36
    // use stable version

    defaultConfig {
        applicationId = "com.example.jetnotes"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
    buildToolsVersion = "35.0.0"
}

dependencies {

    // -------------------------------
    // Versions
    // -------------------------------
    val hiltVersion = "2.51.1"
    val roomVersion = "2.6.1"
    val lifecycleVersion = "2.7.0"
    val navVersion = "2.7.7"

    // -------------------------------
    // ROOM
    // -------------------------------
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // -------------------------------
    // LIFECYCLE
    // -------------------------------
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    // -------------------------------
    // CORE + COMPOSE
    // -------------------------------
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.compose.material:material-icons-extended:1.6.7")

    // -------------------------------
    // NAVIGATION
    // -------------------------------
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // -------------------------------
    // HILT (ONLY CORRECT DEPENDENCIES)
    // -------------------------------
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // -------------------------------
    // TESTING
    // -------------------------------
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

// KAPT configuration – helps fix NonExistentClass annotation issues
kapt {
    correctErrorTypes = true
}
