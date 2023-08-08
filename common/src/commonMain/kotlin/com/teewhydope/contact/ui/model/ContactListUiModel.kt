package com.teewhydope.contact.ui.model

sealed interface ContactListUiModel {
    data object Loading : ContactListUiModel

    data class Contacts(
        val allContacts: List<ContactUiModel> = emptyList(),
        val recentContacts: List<ContactUiModel> = emptyList(),
    ) :
        ContactListUiModel

    data object Empty : ContactListUiModel

    data object Error : ContactListUiModel
}
