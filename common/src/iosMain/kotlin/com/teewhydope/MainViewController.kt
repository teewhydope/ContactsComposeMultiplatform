package com.teewhydope

import com.teewhydope.architecture.ui.navigation.Action
import moe.tlaster.precompose.PreComposeApplication
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
            UIUserInterfaceStyle.UIUserInterfaceStyleDark
    return PreComposeApplication {
        App(
            darkTheme = isDarkTheme,
            dynamicColor = false,
        )
    }
}

fun onBackGesture() {
    store.send(Action.OnBackPressed)
}
