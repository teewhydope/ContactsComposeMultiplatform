package com.teewhydope.contact.presentation.model

sealed interface ContactListNotification {
    data class ContactSelected(val contact: ContactPresentationModel?) :
        ContactListNotification
}
