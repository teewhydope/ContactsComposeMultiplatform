package com.teewhydope.architecture.ui.navigation.mapper

import com.teewhydope.architecture.presentation.navigation.PresentationDestination
import com.teewhydope.architecture.ui.navigation.model.UiDestination

interface DestinationToUiMapper {
    fun toUi(presentationDestination: PresentationDestination): UiDestination
}
