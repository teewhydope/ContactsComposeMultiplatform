package com.teewhydope.app.navigation.mapper

import com.teewhydope.architecture.presentation.navigation.PresentationDestination
import com.teewhydope.architecture.presentation.navigation.PresentationDestination.Back
import com.teewhydope.architecture.ui.navigation.mapper.DestinationToUiMapper
import com.teewhydope.architecture.ui.navigation.model.UiDestination
import moe.tlaster.precompose.navigation.Navigator

class ContactDetailsDestinationToUiMapper(
    private val navControllerProvider: Navigator,
) : DestinationToUiMapper {

    override fun toUi(presentationDestination: PresentationDestination): UiDestination =
        when (presentationDestination) {
            is Back -> BackUiDestination(navControllerProvider)

            else -> error("Unknown destination: $presentationDestination")
        }

    private data class BackUiDestination(
        private val navController: Navigator,
    ) : UiDestination {
        override fun navigate() {
            navController.popBackStack()
        }
    }
}
