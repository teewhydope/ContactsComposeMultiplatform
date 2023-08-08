package com.teewhydope.contact.domain.model

data class ContactListDomainModel(
    val allContacts: Collection<ContactDomainModel> = emptyList(),
    val recentContacts: Collection<ContactDomainModel> = emptyList(),
)
