package com.teewhydope.contact.ui.mapper

import com.teewhydope.architecture.ui.navigation.mapper.UiToPresentationMapper
import com.teewhydope.contact.presentation.model.ContactPresentationModel
import com.teewhydope.contact.ui.model.ContactUiModel

class ContactUiToPresentationMapper :
    UiToPresentationMapper<ContactUiModel, ContactPresentationModel>() {
    override fun map(input: ContactUiModel) =
        ContactPresentationModel(
            id = input.id,
            firstName = input.firstName,
            lastName = input.lastName,
            email = input.email,
            phoneNumber = input.phoneNumber,
            photoBytes = input.photoBytes,

        )
}
