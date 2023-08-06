package com.teewhydope.contact.domain.usecase

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
class DeleteContactUseCaseTest {
    private lateinit var classUnderTest: DeleteContactUseCase

    @Mock
    lateinit var contactRepository: ContactRepository

    @Mock
    lateinit var coroutineContextProvider: CoroutineContextProvider

    private val GIVEN_CONTACT_ID: Long = 1

    @Before
    fun setUp() {
        classUnderTest =
            DeleteContactUseCase(contactRepository, coroutineContextProvider)
    }

    @Test
    fun `Given contact id When executeInBackground Then delete contact`() {
        runBlocking {
            // When
            classUnderTest.executeInBackground(GIVEN_CONTACT_ID)

            // Then
            verify(contactRepository).delete(GIVEN_CONTACT_ID)
        }
    }
}
