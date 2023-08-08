package com.teewhydope.contact.datasource.source.local

import com.teewhydope.contact.datasource.model.ContactDataModel
import kotlinx.coroutines.flow.Flow

interface ContactSource {
    suspend fun allContacts(): Flow<Collection<ContactDataModel>>

    suspend fun recentContacts(limit: Long): Flow<Collection<ContactDataModel>>
    fun insert(contact: ContactDataModel)
    fun delete(id: Long)
}
