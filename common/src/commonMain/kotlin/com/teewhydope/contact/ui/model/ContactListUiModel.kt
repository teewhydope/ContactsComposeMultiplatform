package com.teewhydope.contact.ui.model

sealed interface ContactListUiModel {
    data object Loading : ContactListUiModel

    data class AllContacts(val contacts: List<ContactUiModel> = emptyList()) :
        ContactListUiModel

    data class RecentContacts(val contacts: List<ContactUiModel> = emptyList()) :
        ContactListUiModel

    data object Empty : ContactListUiModel

    data object Error : ContactListUiModel
}
