package com.teewhydope.contact.domain.usecase

import com.teewhydope.contact.domain.model.ContactDomainModel
import com.teewhydope.contact.domain.model.ContactListDomainModel
import com.teewhydope.contact.domain.model.ContactListDomainModel.AllContacts
import com.teewhydope.contact.domain.repository.ContactRepository
import com.teewhydope.coroutine.CoroutineContextProvider
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAllContactsUseCaseTest {
    private lateinit var classUnderTest: GetAllContactsUseCase

    @Mock
    lateinit var contactRepository: ContactRepository

    @Mock
    lateinit var coroutineContextProvider: CoroutineContextProvider

    @Before
    fun setUp() {
        classUnderTest =
            GetAllContactsUseCase(contactRepository, coroutineContextProvider)
    }

    @Test
    fun `Given all contacts when executeInBackground then returns all contacts`() =
        runBlocking {
            // Given
            val expectedResult = AllContacts(
                contacts = (1..10).map {
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

            given(contactRepository.allContacts()).willReturn(flowOf(expectedResult))

            // When
            var actualResult: ContactListDomainModel? = null
            classUnderTest.executeInBackground(Unit) { allContacts ->
                actualResult = allContacts
            }

            // Then
            assertEquals(expectedResult, actualResult)
        }
}
