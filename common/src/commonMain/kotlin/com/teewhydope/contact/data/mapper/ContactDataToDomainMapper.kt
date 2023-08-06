package com.teewhydope.contact.data.mapper

import com.teewhydope.architecture.data.mapper.DataToDomainMapper
import com.teewhydope.contact.datasource.model.ContactDataModel
import com.teewhydope.contact.domain.model.ContactDomainModel

class ContactDataToDomainMapper :
    DataToDomainMapper<ContactDataModel, ContactDomainModel>() {
    override fun map(input: ContactDataModel) =
        ContactDomainModel(
            id = input.id,
            firstName = input.firstName,
            lastName = input.lastName,
            email = input.email,
            phoneNumber = input.phoneNumber,
            photoBytes = input.photoBytes,

        )
}
