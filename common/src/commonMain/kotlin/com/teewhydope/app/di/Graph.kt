package com.teewhydope.app.di

import com.teewhydope.app.database.DatabaseFactory
import com.teewhydope.app.database.DeviceContacts
import com.teewhydope.app.database.DriverFactory
import com.teewhydope.app.navigation.mapper.ContactDetailsDestinationToUiMapper
import com.teewhydope.app.navigation.mapper.ContactHomeDestinationToUiMapper
import com.teewhydope.architecture.domain.UseCaseExecutor
import com.teewhydope.contact.data.mapper.ContactDataToDomainMapper
import com.teewhydope.contact.data.mapper.ContactDomainToDataMapper
import com.teewhydope.contact.data.repository.ContactDataRepository
import com.teewhydope.contact.datasource.implementation.DataBaseDataContactSource
import com.teewhydope.contact.datasource.implementation.DeviceContactDataSource
import com.teewhydope.contact.datasource.implementation.mapper.ContactEntityToDataMapper
import com.teewhydope.contact.datasource.implementation.mapper.ContactListEntityToDataMapper
import com.teewhydope.contact.datasource.implementation.mapper.DeviceContactToDataMapper
import com.teewhydope.contact.domain.usecase.AddContactUseCaseImpl
import com.teewhydope.contact.domain.usecase.DeleteContactUseCaseImpl
import com.teewhydope.contact.domain.usecase.GetContactsUseCaseImpl
import com.teewhydope.contact.presentation.mapper.ContactDomainToPresentationMapper
import com.teewhydope.contact.presentation.mapper.ContactListDomainToPresentationMapper
import com.teewhydope.contact.presentation.viewmodel.ContactDetailsViewModel
import com.teewhydope.contact.presentation.viewmodel.ContactViewModel
import com.teewhydope.contact.ui.mapper.ContactListPresentationToUiMapper
import com.teewhydope.contact.ui.mapper.ContactPresentationToUiMapper
import com.teewhydope.contact.ui.mapper.ContactUiToPresentationMapper
import com.teewhydope.coroutine.CoroutineContextProvider
import com.teewhydope.database.ContactDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.datetime.Clock
import moe.tlaster.precompose.navigation.Navigator

object Graph {
    fun init() {}

    val coroutineScope: CoroutineScope
        get() = MainScope()

    val coroutineContextProvider: CoroutineContextProvider
        get() = CoroutineContextProvider.Default

    private val useCaseExecutor: UseCaseExecutor
        get() = UseCaseExecutor()

    val currentTime: Long
        get() = Clock.System.now().toEpochMilliseconds()

    private val driverFactory: DriverFactory
        get() = DriverFactory()

    val navHostController by lazy {
        Navigator()
    }

    val deviceContacts: DeviceContacts
        get() = DeviceContacts()

    val contactHomeDestinationToUiMapper: ContactHomeDestinationToUiMapper
        get() = ContactHomeDestinationToUiMapper(navHostController)

    val contactDetailsDestinationToUiMapper: ContactDetailsDestinationToUiMapper
        get() = ContactDetailsDestinationToUiMapper(navHostController)

    val contactDatabase: ContactDatabase by lazy {
        DatabaseFactory(driverFactory = driverFactory).createDatabase()
    }

    val contactsSource by lazy {
        DataBaseDataContactSource(
            contactEntityToDataMapper = contactEntityToDataMapper,
            coroutineContextProvider = coroutineContextProvider,
            db = contactDatabase,
            currentTime = currentTime,
        )
    }

    val deviceContactSource by lazy {
        DeviceContactDataSource(
            deviceContactToDataMapper = deviceContactToDataMapper,
            deviceContacts = deviceContacts,
        )
    }

    val contactRepository by lazy {
        ContactDataRepository(
            databaseContactSource = contactsSource,
            deviceContactSource = deviceContactSource,
            contactDomainToDataMapper = contactDomainToDataMapper,
            contactDataToDomainMapper = contactDataToDomainMapper,
        )
    }

    val contactEntityToDataMapper: ContactEntityToDataMapper
        get() = ContactEntityToDataMapper()

    val deviceContactToDataMapper: DeviceContactToDataMapper
        get() = DeviceContactToDataMapper()

    val contactListEntityToDataMapper: ContactListEntityToDataMapper
        get() = ContactListEntityToDataMapper()

    val contactDataToDomainMapper: ContactDataToDomainMapper
        get() = ContactDataToDomainMapper()

    val contactDomainToDataMapper: ContactDomainToDataMapper
        get() = ContactDomainToDataMapper()

    private val contactDomainToPresentationMapper: ContactDomainToPresentationMapper
        get() = ContactDomainToPresentationMapper()

    val contactListDomainToPresentationMapper: ContactListDomainToPresentationMapper
        get() = ContactListDomainToPresentationMapper(contactDomainToPresentationMapper)

    private val contactPresentationToUiMapper: ContactPresentationToUiMapper
        get() = ContactPresentationToUiMapper()

    val contactUiToPresentationMapper: ContactUiToPresentationMapper
        get() = ContactUiToPresentationMapper()

    val contactListPresentationToUiMapper: ContactListPresentationToUiMapper
        get() = ContactListPresentationToUiMapper(contactPresentationToUiMapper)

    val getContactsUseCase by lazy {
        GetContactsUseCaseImpl(
            coroutineContextProvider = coroutineContextProvider,
            contactRepository = contactRepository,
        )
    }

    val addContactUseCase by lazy {
        AddContactUseCaseImpl(
            coroutineContextProvider = coroutineContextProvider,
            contactRepository = contactRepository,
        )
    }

    val deleteContactUseCase by lazy {
        DeleteContactUseCaseImpl(
            coroutineContextProvider = coroutineContextProvider,
            contactRepository = contactRepository,
        )
    }

    val contactViewModel by lazy {
        ContactViewModel(
            contactListDomainToPresentationMapper = contactListDomainToPresentationMapper,
            useCaseExecutor = useCaseExecutor,
            getContactsUseCase = getContactsUseCase,
            deleteContactUseCase = deleteContactUseCase,
            addContactUseCase = addContactUseCase,

        )
    }

    val contactDetailsViewModel by lazy {
        ContactDetailsViewModel(useCaseExecutor = useCaseExecutor)
    }
}
