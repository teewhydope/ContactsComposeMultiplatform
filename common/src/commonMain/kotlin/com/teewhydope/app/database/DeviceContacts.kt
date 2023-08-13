package com.teewhydope.app.database

import com.teewhydope.contact.datasource.model.DeviceContactDataModel

expect class DeviceContacts() {
    fun fetchDeviceContacts(): Collection<DeviceContactDataModel>
}
