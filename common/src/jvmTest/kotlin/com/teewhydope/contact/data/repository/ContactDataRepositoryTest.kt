package com.teewhydope.contact.data.repository

import com.teewhydope.contact.data.mapper.ContactDataToDomainMapper
import com.teewhydope.contact.data.mapper.ContactDomainToDataMapper
import com.teewhydope.contact.datasource.model.ContactDataModel
import com.teewhydope.contact.datasource.source.local.DatabaseContactSource
import com.teewhydope.contact.datasource.source.local.DeviceContactSource
import com.teewhydope.contact.domain.model.ContactDomainModel
import com.teewhydope.contact.domain.model.ContactListDomainModel
import com.teewhydope.coroutine.currentValue
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

val givenData: List<ContactDataModel> = listOf(
    ContactDataModel(
        id = 1,
        firstName = "First",
        lastName = "Last",
        email = "test@gmail.com",
        phoneNumber = "0801111111",
        photoBytes = null,
    ),
)

val expectedResult = ContactListDomainModel(
    allContacts = listOf(
        ContactDomainModel(
            id = 1,
            firstName = "First",
            lastName = "Last",
            email = "test@gmail.com",
            phoneNumber = "0801111111",
            photoBytes = null,
        ),
    ),
    recentContacts = listOf(
        ContactDomainModel(
            id = 1,
            firstName = "First",
            lastName = "Last",
            email = "test@gmail.com",
            phoneNumber = "0801111111",
            photoBytes = null,
        ),
    ),
)

@RunWith(MockitoJUnitRunner::class)
class ContactDataRepositoryTest {
    private lateinit var classUnderTest: ContactDataRepository

    @Mock
    private lateinit var deviceContactSource: DeviceContactSource

    @Mock
    private lateinit var databaseContactSource: DatabaseContactSource

    @Mock
    private lateinit var contactDataToDomainMapper: ContactDataToDomainMapper

    @Mock
    private lateinit var contactDomainToDataMapper: ContactDomainToDataMapper

    @Before
    fun setUp() {
        classUnderTest = ContactDataRepository(
            databaseContactSource,
            deviceContactSource,
            contactDataToDomainMapper,
            contactDomainToDataMapper,
        )
    }

    @Test
    fun `Given allContacts and recentContacts  when contacts then returns all Contacts and recent contacts`() =
        runTest {
            // Given

            given(deviceContactSource.allContacts()).willReturn(
                listOf(
                    ContactDataModel(
                        id = 1,
                        firstName = "First",
                        lastName = "Last",
                        email = "test@gmail.com",
                        phoneNumber = "0801111111",
                        photoBytes = null,
                    ),
                ),
            )

            given(databaseContactSource.allContacts()).willReturn(
                flowOf(
                    listOf(
                        ContactDataModel(
                            id = 1,
                            firstName = "First",
                            lastName = "Last",
                            email = "test@gmail.com",
                            phoneNumber = "0801111111",
                            photoBytes = null,
                        ),
                    ),
                ),
            )

            given(databaseContactSource.recentContacts(6)).willReturn(
                flowOf(
                    listOf(
                        ContactDataModel(
                            id = 1,
                            firstName = "First",
                            lastName = "Last",
                            email = "test@gmail.com",
                            phoneNumber = "0801111111",
                            photoBytes = null,
                        ),
                    ),
                ),
            )

            given(contactDataToDomainMapper.toDomain(givenData.first()))
                .willReturn(expectedResult.recentContacts.first())

            given(contactDataToDomainMapper.toDomain(givenData.first()))
                .willReturn(expectedResult.allContacts.first())

            // When
            val actualResult = classUnderTest.contacts().currentValue()

            // Then
            assertEquals(expectedResult, actualResult)
        }
}
