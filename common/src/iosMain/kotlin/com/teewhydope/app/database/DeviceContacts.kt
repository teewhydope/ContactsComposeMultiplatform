package com.teewhydope.app.database

import com.teewhydope.contact.datasource.model.DeviceContactDataModel
import com.teewhydope.logger
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Contacts.CNContactEmailAddressesKey
import platform.Contacts.CNContactFamilyNameKey
import platform.Contacts.CNContactFetchRequest
import platform.Contacts.CNContactGivenNameKey
import platform.Contacts.CNContactPhoneNumbersKey
import platform.Contacts.CNContactStore
import platform.Foundation.dictionaryWithValuesForKeys

actual class DeviceContacts {
    @OptIn(ExperimentalForeignApi::class)
    actual fun fetchDeviceContacts(): Collection<DeviceContactDataModel> {
        var contactList = mutableListOf<DeviceContactDataModel>()
        val keys = listOf(
            CNContactGivenNameKey,
            CNContactFamilyNameKey,
            CNContactPhoneNumbersKey,
            CNContactEmailAddressesKey,
        )
        val request = CNContactFetchRequest(keysToFetch = keys)
        try {
            CNContactStore().enumerateContactsWithFetchRequest(
                fetchRequest = request,
                error = null,
            ) { contact, _ ->
                val dictionary =
                    contact?.dictionaryWithValuesForKeys(keys = listOf("iOSLegacyIdentifier"))

                contactList.add(
                    DeviceContactDataModel(
                        id = dictionary?.get("iOSLegacyIdentifier").toString().toLong(),
                        firstName = contact?.givenName ?: "",
                        lastName = contact?.familyName ?: "",
                        email = contact?.emailAddresses.toString(),
                        phoneNumber = contact?.phoneNumbers?.first().toString(),
                        photoBytes = null,
                    ),
                )
            }
        } catch (e: Exception) {
            logger.d { "Failed to fetch contacts, reason: $e" }
        }
        contactList = contactList.sortedBy {
            it.firstName
        }.toMutableList()
        return contactList
    }
}
