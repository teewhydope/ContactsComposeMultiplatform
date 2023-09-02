package com.teewhydope.contact.datasource.model

import com.teewhydope.database.ContactEntity

sealed interface ContactListEntityModel {
    data class AllContacts(val contacts: Collection<ContactEntity> = emptyList()) :
        ContactListEntityModel

    data class RecentContacts(val contacts: Collection<ContactEntity> = emptyList()) :
        ContactListEntityModel

    object Empty : ContactListEntityModel
}
