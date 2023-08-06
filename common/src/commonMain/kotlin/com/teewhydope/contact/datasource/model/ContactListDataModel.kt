package com.teewhydope.contact.datasource.model

sealed interface ContactListDataModel {
    data class AllContacts(val contacts: Collection<ContactDataModel> = emptyList()) :
        ContactListDataModel

    data class RecentContacts(val contacts: Collection<ContactDataModel> = emptyList()) :
        ContactListDataModel

    data object Empty : ContactListDataModel
}
