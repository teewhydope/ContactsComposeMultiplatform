package com.teewhydope.contact.domain.usecase

import com.teewhydope.contact.domain.model.ContactDomainModel
import com.teewhydope.contact.domain.repository.ContactRepository
import com.teewhydope.coroutine.CoroutineContextProvider
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class AddContactUseCaseImplTest {
    private lateinit var classUnderTest: AddContactUseCaseImpl

    @Mock
    lateinit var contactRepository: ContactRepository

    @Mock
    lateinit var coroutineContextProvider: CoroutineContextProvider

    private val GIVEN_NEW_CONTACT_INFO_DOMAIN_MODEL = ContactDomainModel(
        id = 1,
        firstName = "First",
        lastName = "Last",
        email = "test@gmail.com",
        phoneNumber = "0801111111",
        photoBytes = null,
    )

    @Before
    fun setUp() {
        classUnderTest =
            AddContactUseCaseImpl(contactRepository, coroutineContextProvider)
    }

    @Test
    fun `Given new contact info When executeInBackground Then info is inserted`() {
        runBlocking {
            // When
            classUnderTest.executeInBackground(GIVEN_NEW_CONTACT_INFO_DOMAIN_MODEL)

            // Then
            verify(contactRepository).insert(GIVEN_NEW_CONTACT_INFO_DOMAIN_MODEL)
        }
    }
}
