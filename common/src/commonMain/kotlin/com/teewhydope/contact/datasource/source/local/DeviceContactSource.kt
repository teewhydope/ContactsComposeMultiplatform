package com.teewhydope.contact.datasource.source.local

import com.teewhydope.contact.datasource.model.ContactDataModel

interface DeviceContactSource {
    suspend fun allContacts(): Collection<ContactDataModel>
}
