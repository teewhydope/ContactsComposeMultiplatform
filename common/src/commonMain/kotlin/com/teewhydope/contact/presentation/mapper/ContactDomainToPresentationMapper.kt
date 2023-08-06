package com.teewhydope.contact.presentation.mapper

import com.teewhydope.architecture.presentation.mapper.DomainToPresentationMapper
import com.teewhydope.contact.domain.model.ContactDomainModel
import com.teewhydope.contact.presentation.model.ContactPresentationModel

class ContactDomainToPresentationMapper :
    DomainToPresentationMapper<ContactDomainModel, ContactPresentationModel>() {
    override fun map(input: ContactDomainModel) =
        ContactPresentationModel(
            id = input.id,
            firstName = input.firstName,
            lastName = input.lastName,
            email = input.email,
            phoneNumber = input.phoneNumber,
            photoBytes = input.photoBytes,

        )
}
