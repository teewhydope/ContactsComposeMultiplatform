package com.teewhydope.contact.ui.model

data class ContactUiModel(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val photoBytes: ByteArray?,
)
