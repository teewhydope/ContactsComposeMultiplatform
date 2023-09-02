import com.teewhydope.buildsrc.Deps
import com.teewhydope.buildsrc.Deps.Mockito.core
import com.teewhydope.buildsrc.Deps.Mockito.inline

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("app.cash.sqldelight")
    id("org.jetbrains.compose") version "1.5.0"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    jvm()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "common"
            binaryOption("bundleId", "com.teewhydope.contactscomposemultiplatform.ios")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                api("org.lighthousegames:logging:1.3.0")
                implementation("app.cash.sqldelight:runtime:2.1.0-SNAPSHOT")
                implementation("app.cash.sqldelight:coroutines-extensions:2.1.0-SNAPSHOT")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation(Deps.Coroutine.core)
                api("moe.tlaster:precompose:1.5.0")
                implementation("org.jetbrains.skiko:skiko:0.7.77")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                // implementation("app.cash.sqldelight:sqlite-driver:2.0.0")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:android-driver:2.1.0-SNAPSHOT")
                implementation("androidx.appcompat:appcompat:1.6.1")
                implementation("androidx.activity:activity-compose:1.7.2")
                implementation("androidx.startup:startup-runtime:1.1.1")
                implementation("com.alexstyl:contactstore:1.7.0")
            }
        }
        val androidUnitTest by getting {
            dependencies {}
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation("app.cash.sqldelight:native-driver:2.1.0-SNAPSHOT")
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

        val jvmMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:sqlite-driver:2.1.0-SNAPSHOT")
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(core)
                implementation(inline)
                implementation(Deps.Mockito.kotlin)
                implementation(Deps.Coroutine.test)
            }
        }
    }
}

android {
    namespace = "com.teewhydope.contactscomposemultiplatform"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

sqldelight {
    databases {
        create("ContactDatabase") {
            packageName.set("com.teewhydope.database")
        }
    }
}
