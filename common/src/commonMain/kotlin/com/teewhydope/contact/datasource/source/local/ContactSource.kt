package com.teewhydope.contact.datasource.source.local

import com.teewhydope.contact.datasource.model.ContactDataModel
import com.teewhydope.contact.datasource.model.ContactListDataModel
import kotlinx.coroutines.flow.Flow

interface ContactSource {
    suspend fun allContacts(): Flow<ContactListDataModel>
    suspend fun recentContacts(limit: Int): Flow<ContactListDataModel>
    fun insert(contact: ContactDataModel)
    fun delete(id: Long)
}
