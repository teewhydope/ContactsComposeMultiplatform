package com.teewhydope.contact.presentation.model

import com.teewhydope.architecture.presentation.model.ViewState

sealed interface ContactDetailsViewState : ViewState {
    data object Loading : ContactDetailsViewState
}
