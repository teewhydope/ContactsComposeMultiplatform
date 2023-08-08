package com.teewhydope

import androidx.compose.runtime.Composable
import com.teewhydope.app.ui.AppNavHost
import com.teewhydope.app.ui.AppTheme
import com.teewhydope.architecture.ui.navigation.createStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.lighthousegames.logging.logging

val logger = logging()
val store = CoroutineScope(SupervisorJob()).createStore()

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        AppNavHost()
    }
}
