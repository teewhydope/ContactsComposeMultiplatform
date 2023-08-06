package com.teewhydope.buildsrc

object Versions {
    const val koin = "3.4.0"
    const val mockito = "5.0.0"
    const val coroutine = "1.7.3"
}

object Deps {

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }

    object Mockito {
        const val core = "org.mockito:mockito-core:${Versions.mockito}"
        const val inline = "org.mockito:mockito-inline:${Versions.mockito}"
        const val kotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito}"
    }

    object Coroutine {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine}"
    }
}
