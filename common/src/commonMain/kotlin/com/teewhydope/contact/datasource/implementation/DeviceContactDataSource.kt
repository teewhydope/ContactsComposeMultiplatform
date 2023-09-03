package com.teewhydope.contact.datasource.implementation

import com.teewhydope.app.database.DeviceContacts
import com.teewhydope.contact.datasource.implementation.mapper.DeviceContactToDataMapper
import com.teewhydope.contact.datasource.model.ContactDataModel
import com.teewhydope.contact.datasource.model.DeviceContactDataModel
import com.teewhydope.contact.datasource.source.local.DeviceContactSource

class DeviceContactDataSource(
    private val deviceContactToDataMapper: DeviceContactToDataMapper,
    private val deviceContacts: DeviceContacts,
) : DeviceContactSource {
    override suspend fun allContacts(): Collection<ContactDataModel> =
        deviceContacts.fetchDeviceContacts().toContactListDataModel()

    private fun Collection<DeviceContactDataModel>.toContactListDataModel(): Collection<ContactDataModel> {
        return this.map { deviceContacts ->
            deviceContactToDataMapper.toData(deviceContacts)
        }
    }
}
