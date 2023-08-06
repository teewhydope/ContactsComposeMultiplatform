package com.teewhydope.contact.presentation.viewmodel

import com.teewhydope.architecture.domain.UseCaseExecutor
import com.teewhydope.architecture.presentation.viewmodel.BaseViewModel
import com.teewhydope.contact.domain.model.ContactDomainModel
import com.teewhydope.contact.domain.usecase.AddContactUseCase
import com.teewhydope.contact.domain.usecase.DeleteContactUseCase
import com.teewhydope.contact.domain.usecase.GetAllContactsUseCase
import com.teewhydope.contact.presentation.mapper.ContactListDomainToPresentationMapper
import com.teewhydope.contact.presentation.model.ContactListNotification
import com.teewhydope.contact.presentation.model.ContactListViewState
import com.teewhydope.contact.presentation.model.ContactListViewState.AllContacts
import com.teewhydope.contact.presentation.model.ContactListViewState.Empty
import com.teewhydope.contact.presentation.model.ContactListViewState.Error
import com.teewhydope.contact.presentation.model.ContactListViewState.Loading
import com.teewhydope.contact.presentation.model.ContactPresentationModel
import com.teewhydope.contact.presentation.model.ErrorPresentationModel.Unknown
import com.teewhydope.logger

class ContactViewModel(
    private val getAllContactsUseCase: GetAllContactsUseCase,
    private val addContactUseCase: AddContactUseCase,
    private val deleteContactUseCase: DeleteContactUseCase,
    private val contactListDomainToPresentationMapper: ContactListDomainToPresentationMapper,
    useCaseExecutor: UseCaseExecutor,
) : BaseViewModel<ContactListViewState, ContactListNotification>(useCaseExecutor) {
    override val initialViewState = Loading

    fun onEnter() {
        fetchAllContacts()
    }

    private fun fetchAllContacts() {
        updateViewState(Loading)
        getAllContactsUseCase.run(
            onResult = { result ->
                logger.d { result }
                val presentationModel =
                    contactListDomainToPresentationMapper.toPresentation(result)
                when (presentationModel) {
                    is AllContacts -> updateViewState(AllContacts(presentationModel.contacts))
                    else -> updateViewState(Empty)
                }
            },
            onException = { exception ->
                logger.e { exception }
                updateViewState(
                    Error(error = Unknown),
                )
            },
        )
    }

    fun onDeleteContact(number: Long) {
        deleteContactUseCase.run(
            value = number,
            onResult = {},
            onException = { exception ->
                logger.e { exception }
                updateViewState(
                    Error(error = Unknown),
                )
            },
        )
    }

    fun onSelectContact(contact: ContactPresentationModel?, id: Int) {
        val ids = (id + 1)
        addContactUseCase.run(
            value = ContactDomainModel(
                id = ids.toLong(),
                firstName = "teewhy$ids",
                lastName = "dope$ids",
                phoneNumber = "0800000001",
                email = "teewhy$ids@test.com",
                photoBytes = null,

            ),
            onResult = { },
            onException = { exception ->
                logger.e { exception }
            },
        )
    }
}
