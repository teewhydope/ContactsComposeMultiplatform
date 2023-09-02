package com.teewhydope.contact.data.mapper

import com.teewhydope.contact.datasource.model.ContactDataModel
import com.teewhydope.contact.domain.model.ContactDomainModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.MethodRule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.junit.MockitoJUnit

@RunWith(Parameterized::class)
class ContactDataToDomainMapperTest(
    private val input: ContactDataModel,
    private val expected: ContactDomainModel
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "Given {0} then returns {1}")
        fun parameters() = listOf(
            arrayOf(
                ContactDataModel(
                    id = 1,
                    firstName = "First",
                    lastName = "Last",
                    email = "test@gmail.com",
                    phoneNumber = "0801111111",
                    photoBytes = null,
                ),
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
    }

    @get:Rule
    val mockitoRule: MethodRule = MockitoJUnit.rule()

    private lateinit var classUnderTest: ContactDataToDomainMapper

    @Before
    fun setup() {
        classUnderTest = ContactDataToDomainMapper()
    }

    @Test
    fun `Given ContactDataModel when toDomain() then returns ContactDomainModel`() {
        // When
        val actual = classUnderTest.toDomain(input)

        // Then
        assertEquals(expected, actual)
    }
}