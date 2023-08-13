package com.teewhydope.contact.datasource.implementation.mapper

import com.teewhydope.contact.datasource.model.ContactDataModel
import com.teewhydope.contact.datasource.model.DeviceContactDataModel

class DeviceContactToDataMapper {

    fun toData(input: DeviceContactDataModel) = ContactDataModel(
        id = input.id,
        firstName = input.firstName,
        lastName = input.lastName,
        email = input.email,
        phoneNumber = input.phoneNumber,
        photoBytes = null,
    )
}
