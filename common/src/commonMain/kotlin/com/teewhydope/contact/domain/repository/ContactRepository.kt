package com.teewhydope.contact.domain.repository

import com.teewhydope.contact.domain.model.ContactDomainModel
import com.teewhydope.contact.domain.model.ContactListDomainModel
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    suspend fun allContacts(): Flow<ContactListDomainModel>

    suspend fun recentContacts(limit: Int): Flow<ContactListDomainModel>

    fun insert(contact: ContactDomainModel)
    fun delete(id: Long)
}
