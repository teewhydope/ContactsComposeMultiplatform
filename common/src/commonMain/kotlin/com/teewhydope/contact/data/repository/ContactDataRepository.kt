package com.teewhydope.contact.data.repository

import com.teewhydope.app.di.Graph
import com.teewhydope.contact.data.mapper.ContactDataToDomainMapper
import com.teewhydope.contact.data.mapper.ContactDomainToDataMapper
import com.teewhydope.contact.datasource.model.ContactDataModel
import com.teewhydope.contact.datasource.source.local.ContactSource
import com.teewhydope.contact.domain.model.ContactDomainModel
import com.teewhydope.contact.domain.model.ContactListDomainModel
import com.teewhydope.contact.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ContactDataRepository(
    private val contactSource: ContactSource,
    private val contactDataToDomainMapper: ContactDataToDomainMapper = Graph.contactDataToDomainMapper,
    private val contactDomainToDataMapper: ContactDomainToDataMapper = Graph.contactDomainToDataMapper,
) : ContactRepository {

    override suspend fun contacts(limit: Long): Flow<ContactListDomainModel> =
        combine(
            contactSource.allContacts(),
            contactSource.recentContacts(limit),
        ) { allContacts, recentContacts ->
            ContactListDomainModel(
                allContacts = allContacts.toContactListDomainModel().sortedBy { it.id },
                recentContacts = recentContacts.toContactListDomainModel().sortedBy { it.id },
            )
        }

    override fun insert(contact: ContactDomainModel) =
        contactSource.insert(contactDomainToDataMapper.toData(contact))

    override fun delete(id: Long) = contactSource.delete(id)

    private fun Collection<ContactDataModel>.toContactListDomainModel(): Collection<ContactDomainModel> {
        return this.map { contactData ->
            contactDataToDomainMapper.toDomain(contactData)
        }
    }
}
