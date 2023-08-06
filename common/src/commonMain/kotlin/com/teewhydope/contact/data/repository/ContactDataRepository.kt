package com.teewhydope.contact.data.repository

import com.teewhydope.app.di.Graph
import com.teewhydope.contact.data.mapper.AllContactListDataToDomainResolver
import com.teewhydope.contact.data.mapper.ContactDomainToDataMapper
import com.teewhydope.contact.datasource.source.local.ContactSource
import com.teewhydope.contact.domain.model.ContactDomainModel
import com.teewhydope.contact.domain.model.ContactListDomainModel
import com.teewhydope.contact.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactDataRepository(
    private val contactSource: ContactSource,
    private val allContactListDataToDomainResolver: AllContactListDataToDomainResolver,
    private val contactDomainToDataMapper: ContactDomainToDataMapper = Graph.contactDomainToDataMapper,
) : ContactRepository {

    override suspend fun allContacts(): Flow<ContactListDomainModel> =
        contactSource.allContacts().map { allContactListDataToDomainResolver.toDomain(it) }

    override suspend fun recentContacts(limit: Int): Flow<ContactListDomainModel> =
        contactSource.recentContacts(limit).map { allContactListDataToDomainResolver.toDomain(it) }

    override fun insert(contact: ContactDomainModel) {
        contactSource.insert(contactDomainToDataMapper.toData(contact))
    }

    override fun delete(id: Long) {
        contactSource.delete(id)
    }
}
