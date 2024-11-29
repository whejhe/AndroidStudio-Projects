plugins {
}

    android {
    namespace = "com.dam.calculadora"
    compileSdk = 34

    defaultConfig {
      applicationId = "com.dam.calculadora"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
       release {
           isMinifyEnabled = false
           proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
       }
    }
    }

  dependencies {

  }