package com.teewhydope.contact.datasource.implementation.mapper

import com.teewhydope.contact.datasource.model.ContactDataModel
import com.teewhydope.database.ContactEntity

class ContactEntityToDataMapper {

    fun toData(input: ContactEntity) = ContactDataModel(
        id = input.id,
        firstName = input.firstName,
        lastName = input.lastName,
        email = input.email,
        phoneNumber = input.phoneNumber,
        photoBytes = null,
    )
}
