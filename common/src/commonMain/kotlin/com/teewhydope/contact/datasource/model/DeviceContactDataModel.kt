package com.teewhydope.contact.datasource.model

data class DeviceContactDataModel(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val photoBytes: ByteArray?,
)
