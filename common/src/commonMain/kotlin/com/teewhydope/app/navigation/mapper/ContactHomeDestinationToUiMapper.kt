package com.teewhydope.app.navigation.mapper

import com.teewhydope.architecture.presentation.navigation.PresentationDestination
import com.teewhydope.architecture.ui.navigation.mapper.DestinationToUiMapper
import com.teewhydope.architecture.ui.navigation.model.UiDestination
import com.teewhydope.contact.presentation.navigation.EditContactPresentationDestination
import com.teewhydope.logger
import moe.tlaster.precompose.navigation.Navigator

class ContactHomeDestinationToUiMapper(
    private val navController: Navigator,
) : DestinationToUiMapper {

    override fun toUi(presentationDestination: PresentationDestination): UiDestination =
        when (presentationDestination) {
            is EditContactPresentationDestination -> {
                ContactDetailsUiDestination(navController)
            }

            else -> error("Unknown destination: $presentationDestination")
        }

    private data class ContactDetailsUiDestination(
        private val navController: Navigator,
    ) : UiDestination {
        override fun navigate() {
            logger.d { "navigate now" }
            navController.navigate("/contact_details")
        }
    }
}
