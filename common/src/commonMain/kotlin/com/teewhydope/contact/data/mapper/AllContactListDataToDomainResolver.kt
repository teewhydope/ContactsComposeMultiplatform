package com.teewhydope.contact.data.mapper

import com.teewhydope.app.di.Graph
import com.teewhydope.architecture.data.mapper.DataToDomainMapper
import com.teewhydope.contact.datasource.model.ContactListDataModel
import com.teewhydope.contact.datasource.model.ContactListDataModel.AllContacts
import com.teewhydope.contact.datasource.model.ContactListDataModel.Empty
import com.teewhydope.contact.datasource.model.ContactListDataModel.RecentContacts
import com.teewhydope.contact.domain.model.ContactListDomainModel

class AllContactListDataToDomainResolver(
    private val contactDataToDomainMapper: ContactDataToDomainMapper = Graph.contactDataToDomainMapper,
) :
    DataToDomainMapper<ContactListDataModel, ContactListDomainModel>() {
    override fun map(input: ContactListDataModel) =
        when (input) {
            is AllContacts -> ContactListDomainModel.AllContacts(
                input.contacts.map { contactDataToDomainMapper.toDomain(it) },
            )

            is RecentContacts -> ContactListDomainModel.RecentContacts(
                input.contacts.map { contactDataToDomainMapper.toDomain(it) },
            )

            Empty -> ContactListDomainModel.Empty
        }
}
