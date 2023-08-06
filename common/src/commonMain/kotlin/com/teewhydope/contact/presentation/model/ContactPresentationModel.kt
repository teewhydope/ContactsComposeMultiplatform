package com.teewhydope.contact.presentation.model

data class ContactPresentationModel(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val photoBytes: ByteArray?,
)
