package com.teewhydope.contact.presentation.mapper

import com.teewhydope.architecture.presentation.mapper.DomainToPresentationMapper
import com.teewhydope.contact.domain.model.ContactListDomainModel
import com.teewhydope.contact.domain.model.ContactListDomainModel.AllContacts
import com.teewhydope.contact.domain.model.ContactListDomainModel.RecentContacts
import com.teewhydope.contact.presentation.model.ContactListViewState
import com.teewhydope.contact.presentation.model.ErrorPresentationModel

class ContactListDomainToPresentationMapper(private val contactDomainToPresentationMapper: ContactDomainToPresentationMapper) :
    DomainToPresentationMapper<ContactListDomainModel, ContactListViewState>() {
    override fun map(input: ContactListDomainModel) =
        when (input) {
            is AllContacts -> ContactListViewState.AllContacts(
                input.contacts.map { contactDomainToPresentationMapper.toPresentation(it) },
            )

            is RecentContacts -> ContactListViewState.RecentContacts(
                input.contacts.map { contactDomainToPresentationMapper.toPresentation(it) },
            )

            else -> {
                ContactListViewState.Error(ErrorPresentationModel.Unknown)
            }
        }
}
