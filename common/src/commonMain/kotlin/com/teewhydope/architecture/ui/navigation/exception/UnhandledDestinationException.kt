package com.teewhydope.architecture.ui.navigation.exception

import com.teewhydope.architecture.presentation.navigation.PresentationDestination

class UnhandledDestinationException(
    destination: PresentationDestination,
) : IllegalArgumentException(
    "Navigation to ${destination::class.simpleName} was not handled.",
)
