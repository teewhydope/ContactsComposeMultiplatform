package com.teewhydope.contact.domain.model

import com.teewhydope.architecture.domain.exception.DomainException

sealed interface ContactListDomainModel {
    data class AllContacts(val contacts: Collection<ContactDomainModel> = emptyList()) :
        ContactListDomainModel

    data class RecentContacts(val contacts: Collection<ContactDomainModel> = emptyList()) :
        ContactListDomainModel

    data object Empty : ContactListDomainModel

    data class Error(val exception: DomainException) : ContactListDomainModel
}
