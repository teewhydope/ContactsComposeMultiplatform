package com.teewhydope.contact.data.mapper

import com.teewhydope.architecture.data.mapper.DomainToDataMapper
import com.teewhydope.contact.datasource.model.ContactDataModel
import com.teewhydope.contact.domain.model.ContactDomainModel

class ContactDomainToDataMapper :
    DomainToDataMapper<ContactDomainModel, ContactDataModel>() {
    override fun map(input: ContactDomainModel) =
        ContactDataModel(
            id = input.id,
            firstName = input.firstName,
            lastName = input.lastName,
            email = input.email,
            phoneNumber = input.phoneNumber,
            photoBytes = input.photoBytes,

        )
}
