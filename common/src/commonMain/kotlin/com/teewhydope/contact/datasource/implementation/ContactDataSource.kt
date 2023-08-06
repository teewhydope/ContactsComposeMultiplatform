package com.teewhydope.contact.datasource.implementation

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.teewhydope.app.di.Graph
import com.teewhydope.contact.datasource.implementation.mapper.ContactEntityToDataMapper
import com.teewhydope.contact.datasource.model.ContactDataModel
import com.teewhydope.contact.datasource.model.ContactListDataModel
import com.teewhydope.contact.datasource.model.ContactListDataModel.AllContacts
import com.teewhydope.contact.datasource.model.ContactListDataModel.RecentContacts
import com.teewhydope.contact.datasource.source.local.ContactSource
import com.teewhydope.database.ContactDatabase
import database.ContactEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactDataSource(
    private val contactEntityToDataMapper: ContactEntityToDataMapper,
    private val currentTime: Long = Graph.currentTime,
    db: ContactDatabase,
) : ContactSource {
    private val queries = db.contactQueries

    override suspend fun allContacts(): Flow<ContactListDataModel> =
        queries
            .getContacts().asFlow().mapToList().map { contactEntities ->
                AllContacts(contactEntities.toContactListDataModel())
            }

    override suspend fun recentContacts(limit: Int): Flow<ContactListDataModel> =
        queries
            .getRecentContacts(limit.toLong()).asFlow().mapToList().map { contactEntities ->
                RecentContacts(contactEntities.toContactListDataModel())
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
