package com.teewhydope.app.database

import android.annotation.SuppressLint
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone.CONTACT_ID
import android.provider.ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
import com.teewhydope.contact.datasource.model.DeviceContactDataModel

actual class DeviceContacts {
    @SuppressLint("Range")
    actual fun fetchDeviceContacts(): Collection<DeviceContactDataModel> {
        val contacts = mutableListOf<DeviceContactDataModel>()
        val cursor = applicationContext.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            DISPLAY_NAME + " ASC",
        )
        while (cursor?.moveToNext() == true) {
            contacts.add(
                DeviceContactDataModel(
                    id = cursor.getString(cursor.getColumnIndex(CONTACT_ID)).toLong(),
                    firstName = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME)),
                    lastName = "",
                    email = "",
                    phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                    photoBytes = null,
                ),
            )
        }
        cursor?.close()
        return contacts
    }
}
