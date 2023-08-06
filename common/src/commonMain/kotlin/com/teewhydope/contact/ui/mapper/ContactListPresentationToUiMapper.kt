package com.teewhydope.contact.ui.mapper

import com.teewhydope.architecture.ui.navigation.mapper.PresentationToUiMapper
import com.teewhydope.contact.presentation.model.ContactListViewState
import com.teewhydope.contact.presentation.model.ContactListViewState.AllContacts
import com.teewhydope.contact.presentation.model.ContactListViewState.Empty
import com.teewhydope.contact.presentation.model.ContactListViewState.Loading
import com.teewhydope.contact.presentation.model.ContactListViewState.RecentContacts
import com.teewhydope.contact.ui.model.ContactListUiModel

class ContactListPresentationToUiMapper(
    private val contactPresentationToUiMapper: ContactPresentationToUiMapper,
) :
    PresentationToUiMapper<ContactListViewState, ContactListUiModel>() {
    override fun map(input: ContactListViewState) =
        when (input) {
            Loading -> ContactListUiModel.Loading
            is AllContacts -> ContactListUiModel.AllContacts(
                input.contacts.map { contactPresentationToUiMapper.toUi(it) },
            )

            is RecentContacts -> ContactListUiModel.RecentContacts(
                input.contacts.map { contactPresentationToUiMapper.toUi(it) },
            )

            Empty -> ContactListUiModel.Empty

            else -> {
                ContactListUiModel.Error
            }
        }
}
