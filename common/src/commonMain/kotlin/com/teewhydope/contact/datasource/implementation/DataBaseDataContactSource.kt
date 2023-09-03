package com.teewhydope.contact.datasource.implementation

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.teewhydope.contact.datasource.implementation.mapper.ContactEntityToDataMapper
import com.teewhydope.contact.datasource.model.ContactDataModel
import com.teewhydope.contact.datasource.model.ContactListDataModel.AllContacts
import com.teewhydope.contact.datasource.model.ContactListDataModel.RecentContacts
import com.teewhydope.contact.datasource.source.local.DatabaseContactSource
import com.teewhydope.coroutine.CoroutineContextProvider
import com.teewhydope.database.ContactDatabase
import com.teewhydope.database.ContactEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataBaseDataContactSource(
    private val contactEntityToDataMapper: ContactEntityToDataMapper,
    private val currentTime: Long,
    private val coroutineContextProvider: CoroutineContextProvider,
    db: ContactDatabase,
) : DatabaseContactSource {
    private val queries = db.contactQueries

    override suspend fun allContacts(): Flow<Collection<ContactDataModel>> =
        queries
            .getContacts().asFlow().mapToList(coroutineContextProvider.default)
            .map { contactEntities ->
                AllContacts(contactEntities.toContactListDataModel()).contacts
            }

    override suspend fun recentContacts(limit: Long): Flow<Collection<ContactDataModel>> =
        queries
            .getRecentContacts(limit).asFlow().mapToList(coroutineContextProvider.default)
            .map { contactEntities ->
                RecentContacts(contactEntities.toContactListDataModel()).contacts
            }

    override fun insert(contact: ContactDataModel) {
        queries.insertContact(
            id = contact.id,
            firstName = contact.firstName,
            lastName = contact.lastName,
            email = contact.email,
            phoneNumber = contact.phoneNumber,
            createdAt = currentTime,
            imagePath = null,

        )
    }

    override fun delete(id: Long) {
        queries.deleteContact(id)
    }

    private fun List<ContactEntity>.toContactListDataModel(): Collection<ContactDataModel> {
        return this.map { contactEntity ->
            contactEntityToDataMapper.toData(contactEntity)
        }
    }
}
