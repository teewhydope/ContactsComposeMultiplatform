package com.teewhydope.contact.presentation.mapper

import com.teewhydope.architecture.presentation.mapper.DomainToPresentationMapper
import com.teewhydope.contact.domain.model.ContactListDomainModel
import com.teewhydope.contact.presentation.model.ContactListViewState

class ContactListDomainToPresentationMapper(private val contactDomainToPresentationMapper: ContactDomainToPresentationMapper) :
    DomainToPresentationMapper<ContactListDomainModel, ContactListViewState>() {
    override fun map(input: ContactListDomainModel) =
        ContactListViewState.Contacts(
            allContacts = input.allContacts.map {
                contactDomainToPresentationMapper.toPresentation(
                    it,
                )
            },
            recentContacts = input.recentContacts.map {
                contactDomainToPresentationMapper.toPresentation(
                    it,
                )
            },

        )
}
