package com.teewhydope.contact.presentation.viewmodel

import com.teewhydope.architecture.presentation.BaseViewModelTest
import com.teewhydope.contact.domain.model.ContactDomainModel
import com.teewhydope.contact.domain.model.ContactListDomainModel
import com.teewhydope.contact.domain.usecase.AddContactUseCase
import com.teewhydope.contact.domain.usecase.DeleteContactUseCase
import com.teewhydope.contact.domain.usecase.GetContactsUseCaseImpl
import com.teewhydope.contact.presentation.mapper.ContactListDomainToPresentationMapper
import com.teewhydope.contact.presentation.model.ContactListNotification
import com.teewhydope.contact.presentation.model.ContactListViewState
import com.teewhydope.contact.presentation.model.ContactListViewState.Contacts
import com.teewhydope.contact.presentation.model.ContactListViewState.Loading
import com.teewhydope.contact.presentation.model.ContactPresentationModel
import com.teewhydope.coroutine.currentValue
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given

@RunWith(MockitoJUnitRunner::class)
class ContactViewModelTest :
    BaseViewModelTest<ContactListViewState, ContactListNotification, ContactViewModel>() {

    override val expectedInitialState: ContactListViewState = Loading

    @Mock
    private lateinit var getContactsUseCase: GetContactsUseCaseImpl

    @Mock
    private lateinit var addContactUseCase: AddContactUseCase

    @Mock
    private lateinit var deleteContactUseCase: DeleteContactUseCase

    @Mock
    private lateinit var contactListDomainToPresentationMapper: ContactListDomainToPresentationMapper

    @Before
    fun setUp() {
        classUnderTest = ContactViewModel(
            getContactsUseCase,
            addContactUseCase,
            deleteContactUseCase,
            contactListDomainToPresentationMapper,
            useCaseExecutor,
        )
    }

    @Test
    fun `Given contacts when onEnter then presents contacts`() = runTest {
        // Given
        val givenContacts = ContactListDomainModel(
            allContacts = (1..3).map {
                ContactDomainModel(
                    id = it.toLong(),
                    firstName = "First$it",
                    lastName = "Last$it",
                    email = "test$it@gmail.com",
                    phoneNumber = "0801111111$it",
                    photoBytes = null,
                )
            },
            recentContacts = (1..3).map {
                ContactDomainModel(
                    id = it.toLong(),
                    firstName = "First$it",
                    lastName = "Last$it",
                    email = "test$it@gmail.com",
                    phoneNumber = "0801111111$it",
                    photoBytes = null,
                )
            },
        )

        givenSuccessfulUseCaseExecution(
            getContactsUseCase,
            givenContacts,
        )
        val expectedViewState = Contacts(
            allContacts = (1..3).map {
                ContactPresentationModel(
                    id = it.toLong(),
                    firstName = "First$it",
                    lastName = "Last$it",
                    email = "test$it@gmail.com",
                    phoneNumber = "0801111111$it",
                    photoBytes = null,
                )
            },
            recentContacts = (1..3).map {
                ContactPresentationModel(
                    id = it.toLong(),
                    firstName = "First$it",
                    lastName = "Last$it",
                    email = "test$it@gmail.com",
                    phoneNumber = "0801111111$it",
                    photoBytes = null,
                )
            },
        )
        given { contactListDomainToPresentationMapper.toPresentation(givenContacts) }
            .willReturn(expectedViewState)

        // When
        classUnderTest.onEnter()

        // Then
        val actualValue = classUnderTest.viewState.currentValue()
        assertEquals(expectedViewState, actualValue)
    }
}
