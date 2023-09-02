package com.teewhydope.contact.datasource.databasecontact

import com.teewhydope.app.database.DriverFactory
import com.teewhydope.contact.datasource.implementation.DataBaseDataContactSource
import com.teewhydope.contact.datasource.implementation.mapper.ContactEntityToDataMapper
import com.teewhydope.contact.datasource.model.ContactDataModel
import com.teewhydope.coroutine.currentValue
import com.teewhydope.coroutine.fakeCoroutineContextProvider
import com.teewhydope.database.ContactDatabase
import com.teewhydope.database.ContactEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DataBaseDataContactSourceTest {

    private lateinit var classUnderTest: DataBaseDataContactSource

    @Mock
    private lateinit var contactEntityToDataMapper: ContactEntityToDataMapper

    private lateinit var contactDatabase: ContactDatabase

    private var currentTime: Long = 2

    @Before
    fun setUp() {
        contactDatabase = ContactDatabase(driver = DriverFactory().createDriver())

        classUnderTest = DataBaseDataContactSource(
            contactEntityToDataMapper,
            currentTime,
            fakeCoroutineContextProvider,
            contactDatabase,
        )
    }

    @Test
    fun `Given no contacts fetched when allContacts then returns an empty collection`() =
        runTest {
            // When
            val actualRecords = classUnderTest.allContacts()

            // Then
            assert(actualRecords.first().isEmpty())
        }

    @Test
    fun `Given contacts was fetched when allContacts then returns fetched contacts`() =
        runTest {
            // Given

            val contact = ContactDataModel(
                id = 1,
                firstName = "First",
                lastName = "Last",
                email = "test@gmail.com",
                phoneNumber = "0801111111",
                photoBytes = null,
            )
            val databaseContact = ContactEntity(
                id = 1,
                firstName = "First",
                lastName = "Last",
                email = "test@gmail.com",
                phoneNumber = "0801111111",
                imagePath = null,
                createdAt = currentTime,
            )

            classUnderTest.insert(contact)

            given(contactEntityToDataMapper.toData(databaseContact))
                .willReturn(contact)

            val expectedResult = listOf(
                ContactDataModel(
                    id = 1,
                    firstName = "First",
                    lastName = "Last",
                    email = "test@gmail.com",
                    phoneNumber = "0801111111",
                    photoBytes = null,
                ),
            )

            val expectedCollectionSize = 1

            // When
            val actualResult = classUnderTest.allContacts().currentValue()

            // Then
            assertEquals(expectedCollectionSize, actualResult.size)
            assertEquals(expectedResult, actualResult)
        }
}
