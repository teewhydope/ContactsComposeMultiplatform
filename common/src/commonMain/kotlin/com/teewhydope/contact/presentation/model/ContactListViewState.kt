package com.teewhydope.contact.presentation.model

import com.teewhydope.architecture.presentation.model.ViewState

sealed interface ContactListViewState : ViewState {
    data object Loading : ContactListViewState

    data class Contacts(
        val allContacts: List<ContactPresentationModel> = emptyList(),
        val recentContacts: List<ContactPresentationModel> = emptyList(),
    ) :
        ContactListViewState

    data object Empty : ContactListViewState

    data class Error(val error: ErrorPresentationModel) : ContactListViewState
}

sealed interface ErrorPresentationModel {
    data class Api(val message: String?) : ErrorPresentationModel
    data object RequestTimeout : ErrorPresentationModel
    data object Unknown : ErrorPresentationModel
}
