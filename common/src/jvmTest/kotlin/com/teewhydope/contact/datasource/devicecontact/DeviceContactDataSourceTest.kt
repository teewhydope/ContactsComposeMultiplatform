package com.teewhydope.contact.datasource.devicecontact

import com.teewhydope.app.database.DeviceContacts
import com.teewhydope.contact.datasource.implementation.DeviceContactDataSource
import com.teewhydope.contact.datasource.implementation.mapper.DeviceContactToDataMapper
import com.teewhydope.contact.datasource.model.ContactDataModel
import com.teewhydope.contact.datasource.model.DeviceContactDataModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DeviceContactDataSourceTest {
    private lateinit var classUnderTest: DeviceContactDataSource

    @Mock
    private lateinit var deviceContactToDataMapper: DeviceContactToDataMapper

    @Mock
    private lateinit var deviceContacts: DeviceContacts

    @Before
    fun setUp() {
        classUnderTest = DeviceContactDataSource(
            deviceContactToDataMapper,
            deviceContacts,
        )
    }

    @Test
    fun `Given no contacts fetched when allContacts then returns an empty collection`() = runTest {
        // When
        val actualRecords = classUnderTest.allContacts()

        // Then
        assert(actualRecords.isEmpty())
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
            val deviceContact = DeviceContactDataModel(
                id = 1,
                firstName = "First",
                lastName = "Last",
                email = "test@gmail.com",
                phoneNumber = "0801111111",
                photoBytes = null,
            )

            given(deviceContacts.fetchDeviceContacts()).willReturn(
                listOf(
                    DeviceContactDataModel(
                        id = 1,
                        firstName = "First",
                        lastName = "Last",
                        email = "test@gmail.com",
                        phoneNumber = "0801111111",
                        photoBytes = null,
                    ),
                ),
            )

            given(deviceContactToDataMapper.toData(deviceContact))
                .willReturn(contact)

            val expectedContacts = listOf(
                ContactDataModel(
                    id = 1,
                    firstName = "First",
                    lastName = "Last",
                    email = "test@gmail.com",
                    phoneNumber = "0801111111",
                    photoBytes = null,
                ),
            )

            // When
            val actualResult = classUnderTest.allContacts()

            // Then
            assertEquals(expectedContacts, actualResult)
        }
}
