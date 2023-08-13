package com.teewhydope.contact.presentation.viewmodel

import com.teewhydope.architecture.domain.UseCaseExecutor
import com.teewhydope.architecture.presentation.viewmodel.BaseViewModel
import com.teewhydope.contact.domain.model.ContactDomainModel
import com.teewhydope.contact.domain.usecase.AddContactUseCase
import com.teewhydope.contact.domain.usecase.DeleteContactUseCase
import com.teewhydope.contact.domain.usecase.GetContactsUseCase
import com.teewhydope.contact.presentation.mapper.ContactListDomainToPresentationMapper
import com.teewhydope.contact.presentation.model.ContactListNotification
import com.teewhydope.contact.presentation.model.ContactListViewState
import com.teewhydope.contact.presentation.model.ContactListViewState.Contacts
import com.teewhydope.contact.presentation.model.ContactListViewState.Empty
import com.teewhydope.contact.presentation.model.ContactListViewState.Error
import com.teewhydope.contact.presentation.model.ContactListViewState.Loading
import com.teewhydope.contact.presentation.model.ContactPresentationModel
import com.teewhydope.contact.presentation.model.ErrorPresentationModel.Unknown
import com.teewhydope.contact.presentation.navigation.EditContactPresentationDestination
import com.teewhydope.logger

class ContactViewModel(
    private val getContactsUseCase: GetContactsUseCase,
    private val addContactUseCase: AddContactUseCase,
    private val deleteContactUseCase: DeleteContactUseCase,
    private val contactListDomainToPresentationMapper: ContactListDomainToPresentationMapper,
    useCaseExecutor: UseCaseExecutor,
) : BaseViewModel<ContactListViewState, ContactListNotification>(useCaseExecutor) {
    override val initialViewState = Loading

    fun onEnter() {
        fetchContacts()
    }

    private fun fetchContacts() {
        updateViewState(Loading)
        getContactsUseCase.run(
            onResult = { result ->
                logger.d { result }
                val model =
                    contactListDomainToPresentationMapper.toPresentation(result)
                when (model) {
                    is Contacts -> {
                        updateViewState(
                            Contacts(
                                allContacts = model.allContacts,
                                recentContacts = model.recentContacts,
                            ),
                        )
                    }

                    else -> Empty
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

    fun onSelectContact(contact: ContactPresentationModel? = null, id: Int) {
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

    fun onEditContactAction() {
        navigate(EditContactPresentationDestination)
    }
}
