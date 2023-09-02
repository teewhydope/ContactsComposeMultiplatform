import org.apache.tools.ant.taskdefs.condition.Os
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath("app.cash.sqldelight:gradle-plugin:2.1.0-SNAPSHOT")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.1")
    }
}

plugins {
    id("com.android.application").version("8.0.2").apply(false)
    id("com.android.library").version("8.0.2").apply(false)
    id("org.jetbrains.compose").version("1.5.0").apply(false)
    id("org.jlleitschuh.gradle.ktlint").version("11.5.0")
    id("io.gitlab.arturbosch.detekt").version("1.23.1")
    kotlin("android").version("1.9.0").apply(false)
    kotlin("multiplatform").version("1.9.0").apply(false)
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

subprojects {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

tasks.register("installGitHook", Copy::class) {
    var suffix = "macos"
    if (Os.isFamily(Os.FAMILY_WINDOWS)) {
        suffix = "windows"
    }
    var targetPath = "../.git/modules/ContactComposeMultiplatform"
    if ((File(project.projectDir, ".git")).isDirectory) {
        targetPath = ".git"
    }
    from(file("$rootDir/.automation/scripts/pre-commit-$suffix"))
    into(file("$rootDir/$targetPath/hooks"))
    fileMode = 0b111111101
}

tasks.register("installCodeStyle", Copy::class) {
    from(file("$rootDir/.automation/codeStyles/Project.xml"))
    into(file("$rootDir/.idea/codeStyles"))
}
tasks.getByPath(":common:assemble")
    .dependsOn(listOf(tasks.named("installGitHook"), tasks.named("installCodeStyle")))

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
