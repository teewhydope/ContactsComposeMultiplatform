import com.teewhydope.buildsrc.Deps
import com.teewhydope.buildsrc.Deps.Mockito.core
import com.teewhydope.buildsrc.Deps.Mockito.inline

plugins {
    kotlin("multiplatform")
    id("com.google.devtools.ksp") version "1.9.0-1.0.11"
    id("com.android.library")
    id("org.jetbrains.compose") version "1.5.0-beta01"
    id("com.squareup.sqldelight")
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
        }
    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
                if (name != "kspCommonMainKotlinMetadata") dependsOn("kspCommonMainKotlinMetadata")
            }
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                api("org.lighthousegames:logging:1.3.0")
                implementation("com.squareup.sqldelight:runtime:1.5.5")
                implementation("com.squareup.sqldelight:coroutines-extensions:1.5.5")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation(Deps.Coroutine.core)
                implementation("org.jetbrains.skiko:skiko:0.7.72")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:1.5.5")
                implementation("androidx.appcompat:appcompat:1.6.1")
                implementation("androidx.activity:activity-compose:1.7.2")
                implementation("androidx.startup:startup-runtime:1.1.1")
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:1.5.5")
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

        val jvmTest by getting {
            dependencies {
                with(Deps) {
                    implementation(kotlin("test-junit"))
                    implementation(core)
                    implementation(inline)
                    implementation(Deps.Mockito.kotlin)
                    implementation(Deps.Coroutine.test)
                }
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
    database("ContactDatabase") {
        packageName = "com.teewhydope.database"
        sourceFolders = listOf("sqldelight")
    }
}

dependencies {
    add("kspCommonMainMetadata", "me.tatarka.inject:kotlin-inject-compiler-ksp:0.6.1")
}
