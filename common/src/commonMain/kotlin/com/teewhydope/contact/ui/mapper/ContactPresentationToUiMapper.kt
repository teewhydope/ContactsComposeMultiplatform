package com.teewhydope.contact.ui.mapper

import com.teewhydope.architecture.ui.navigation.mapper.PresentationToUiMapper
import com.teewhydope.contact.presentation.model.ContactPresentationModel
import com.teewhydope.contact.ui.model.ContactUiModel

class ContactPresentationToUiMapper :
    PresentationToUiMapper<ContactPresentationModel, ContactUiModel>() {
    override fun map(input: ContactPresentationModel) =
        ContactUiModel(
            id = input.id,
            firstName = input.firstName,
            lastName = input.lastName,
            email = input.email,
            phoneNumber = input.phoneNumber,
            photoBytes = input.photoBytes,

        )
}
