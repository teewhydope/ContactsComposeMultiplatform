package com.teewhydope.contact.ui.mapper

import com.teewhydope.architecture.ui.navigation.mapper.PresentationToUiMapper
import com.teewhydope.contact.presentation.model.ContactListViewState
import com.teewhydope.contact.presentation.model.ContactListViewState.Contacts
import com.teewhydope.contact.presentation.model.ContactListViewState.Empty
import com.teewhydope.contact.presentation.model.ContactListViewState.Error
import com.teewhydope.contact.presentation.model.ContactListViewState.Loading
import com.teewhydope.contact.ui.model.ContactListUiModel

class ContactListPresentationToUiMapper(
    private val contactPresentationToUiMapper: ContactPresentationToUiMapper,
) :
    PresentationToUiMapper<ContactListViewState, ContactListUiModel>() {
    override fun map(input: ContactListViewState) =
        when (input) {
            Loading -> ContactListUiModel.Loading
            is Contacts -> ContactListUiModel.Contacts(
                allContacts = input.allContacts.map { contactPresentationToUiMapper.toUi(it) },
                recentContacts = input.recentContacts.map { contactPresentationToUiMapper.toUi(it) },
            )

            Empty -> ContactListUiModel.Empty

            is Error -> ContactListUiModel.Error(
                error = "unknown error",
            )
        }
}
