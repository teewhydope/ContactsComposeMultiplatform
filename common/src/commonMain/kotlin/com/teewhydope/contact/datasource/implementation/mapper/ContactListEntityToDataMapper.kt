package com.teewhydope.contact.datasource.implementation.mapper

import com.teewhydope.app.di.Graph
import com.teewhydope.contact.datasource.model.ContactListDataModel
import com.teewhydope.contact.datasource.model.ContactListEntityModel
import com.teewhydope.contact.datasource.model.ContactListEntityModel.AllContacts
import com.teewhydope.contact.datasource.model.ContactListEntityModel.Empty
import com.teewhydope.contact.datasource.model.ContactListEntityModel.RecentContacts

class ContactListEntityToDataMapper(
    private val contactEntityToDataMapper: ContactEntityToDataMapper = Graph.contactEntityToDataMapper,
) {

    fun toData(input: ContactListEntityModel) = when (input) {
        is AllContacts -> ContactListDataModel.AllContacts(
            input.contacts.map { contactEntityToDataMapper.toData(it) },
        )

        is RecentContacts -> ContactListDataModel.RecentContacts(
            input.contacts.map { contactEntityToDataMapper.toData(it) },
        )

        Empty -> ContactListDataModel.Empty
    }
}
