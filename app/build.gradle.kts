import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.hilt)
    alias(libs.plugins.kotlin.kapt)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.20"

}

android {

    val props = Properties().apply {
        val file = rootProject.file("config.properties")
        if (file.exists()) file.inputStream().use { load(it) }
        else error("Missing config.properties")
    }

    namespace = props["APPLICATIONID"].toString()
    compileSdk = props["COMPILESDK"].toString().toInt()

    defaultConfig {
        applicationId = props["APPLICATIONID"].toString()
        minSdk = props["MINSDK"].toString().toInt()
        targetSdk = props["TARGETSDK"].toString().toInt()
        versionCode = props["VERSIONCODE"].toString().toInt()
        versionName = props["VERSIONNAME"].toString()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
       // buildConfigField("String", "BASE_URL", "\"https://api.example.com/\"")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    flavorDimensions += "environment"

    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            resValue("string", "app_name", "${props["APP_NAME"]} - dev")
            buildConfigField("String", "BASE_URL", "\"${props["BASE_URL_STG"]}\"")
        }
        create("prod") {
            dimension = "environment"
            resValue("string", "app_name", props["APP_NAME"].toString())
            buildConfigField("String", "BASE_URL", "\"${props["BASE_URL_PROD"]}\"")
        }
    }

    signingConfigs {

    }


    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }



//    androidResources {
//        generateLocaleConfig = true
//    }

}

dependencies {

    implementation(libs.google.material)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.hilt.android)
    //implementation(libs.hilt.compiler)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.appcompat)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlinx.serialization)
    //

    // icon
    implementation(libs.androidx.compose.material.icons.extended)

    // coil
    implementation(libs.bundles.coil.base)


    // test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}