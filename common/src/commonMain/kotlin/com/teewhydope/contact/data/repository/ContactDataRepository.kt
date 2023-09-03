package com.teewhydope.contact.data.repository

import com.teewhydope.contact.data.mapper.ContactDataToDomainMapper
import com.teewhydope.contact.data.mapper.ContactDomainToDataMapper
import com.teewhydope.contact.datasource.model.ContactDataModel
import com.teewhydope.contact.datasource.source.local.DatabaseContactSource
import com.teewhydope.contact.datasource.source.local.DeviceContactSource
import com.teewhydope.contact.domain.model.ContactDomainModel
import com.teewhydope.contact.domain.model.ContactListDomainModel
import com.teewhydope.contact.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ContactDataRepository(
    private val databaseContactSource: DatabaseContactSource,
    private val deviceContactSource: DeviceContactSource,
    private val contactDataToDomainMapper: ContactDataToDomainMapper,
    private val contactDomainToDataMapper: ContactDomainToDataMapper,
) : ContactRepository {

    override suspend fun contacts(): Flow<ContactListDomainModel> {
        deviceContactSource.allContacts().map { contact ->
            databaseContactSource.insert(contact)
        }.also {
            return combine(
                databaseContactSource.allContacts(),
                databaseContactSource.recentContacts(6),
            ) { allContacts, recentContacts ->
                ContactListDomainModel(
                    allContacts = allContacts.toContactListDomainModel().sortedBy { it.firstName },
                    recentContacts = recentContacts.toContactListDomainModel()
                        .sortedBy { it.firstName },
                )
            }
        }
    }

    override fun insert(contact: ContactDomainModel) {
        // TODO: reimplement
        // databaseContactSource.insert(contactDomainToDataMapper.toData(contact))
    }

    override fun delete(id: Long) {
        // TODO: reimplement
        // databaseContactSource.delete(id)
    }

    private fun Collection<ContactDataModel>.toContactListDomainModel(): Collection<ContactDomainModel> {
        return this.map { contactData ->
            contactDataToDomainMapper.toDomain(contactData)
        }
    }
}
