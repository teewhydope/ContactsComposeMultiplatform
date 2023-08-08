package com.teewhydope.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.teewhydope.app.di.Graph
import com.teewhydope.contact.ui.screens.ContactDetailScreen
import com.teewhydope.contact.ui.screens.ContactListScreen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navigator: Navigator = Graph.navHostController,
    initialRoute: String = "/contact_home",
) {
    NavHost(
        modifier = modifier,
        navigator = navigator,
        initialRoute = initialRoute,
    ) {
        scene("/contact_home") {
            ContactListScreen()
        }
        scene("/contact_details") {
            ContactDetailScreen()
        }
    }
}
