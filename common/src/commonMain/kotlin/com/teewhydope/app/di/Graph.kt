package com.teewhydope.app.di

import com.teewhydope.app.database.DatabaseFactory
import com.teewhydope.app.database.DriverFactory
import com.teewhydope.architecture.domain.UseCaseExecutor
import com.teewhydope.contact.data.mapper.AllContactListDataToDomainResolver
import com.teewhydope.contact.data.mapper.ContactDataToDomainMapper
import com.teewhydope.contact.data.mapper.ContactDomainToDataMapper
import com.teewhydope.contact.data.repository.ContactDataRepository
import com.teewhydope.contact.datasource.implementation.ContactDataSource
import com.teewhydope.contact.datasource.implementation.mapper.ContactEntityToDataMapper
import com.teewhydope.contact.datasource.implementation.mapper.ContactListEntityToDataMapper
import com.teewhydope.contact.domain.usecase.AddContactUseCase
import com.teewhydope.contact.domain.usecase.DeleteContactUseCase
import com.teewhydope.contact.domain.usecase.GetAllContactsUseCase
import com.teewhydope.contact.domain.usecase.GetRecentContactsUseCase
import com.teewhydope.contact.presentation.mapper.ContactDomainToPresentationMapper
import com.teewhydope.contact.presentation.mapper.ContactListDomainToPresentationMapper
import com.teewhydope.contact.presentation.viewmodel.ContactViewModel
import com.teewhydope.contact.ui.mapper.ContactListPresentationToUiMapper
import com.teewhydope.contact.ui.mapper.ContactPresentationToUiMapper
import com.teewhydope.contact.ui.mapper.ContactUiToPresentationMapper
import com.teewhydope.coroutine.CoroutineContextProvider
import com.teewhydope.database.ContactDatabase
import kotlinx.datetime.Clock

object Graph {
    fun init() {}

    val coroutineContextProvider: CoroutineContextProvider
        get() = CoroutineContextProvider.Default

    private val useCaseExecutor: UseCaseExecutor
        get() = UseCaseExecutor()

    val currentTime: Long
        get() = Clock.System.now().toEpochMilliseconds()

    private val driverFactory: DriverFactory
        get() = DriverFactory()

    val contactDatabase: ContactDatabase by lazy {
        DatabaseFactory(driverFactory = driverFactory).createDatabase()
    }

    val contactsSource by lazy {
        ContactDataSource(
            contactEntityToDataMapper = contactEntityToDataMapper,
            db = contactDatabase,
        )
    }

    val contactRepository by lazy {
        ContactDataRepository(
            contactSource = contactsSource,
            allContactListDataToDomainResolver = allContactListDataToDomainResolver,
            contactDomainToDataMapper = contactDomainToDataMapper,
        )
    }

    val contactEntityToDataMapper: ContactEntityToDataMapper
        get() = ContactEntityToDataMapper()

    val contactListEntityToDataMapper: ContactListEntityToDataMapper
        get() = ContactListEntityToDataMapper()

    val contactDataToDomainMapper: ContactDataToDomainMapper
        get() = ContactDataToDomainMapper()

    val allContactListDataToDomainResolver: AllContactListDataToDomainResolver
        get() = AllContactListDataToDomainResolver(contactDataToDomainMapper)

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

    val getAllContactsUseCase by lazy {
        GetAllContactsUseCase(
            coroutineContextProvider = coroutineContextProvider,
            contactRepository = contactRepository,
        )
    }

    val getRecentContactsUseCase by lazy {
        GetRecentContactsUseCase(
            coroutineContextProvider = coroutineContextProvider,
            contactRepository = contactRepository,
        )
    }

    val addContactUseCase by lazy {
        AddContactUseCase(
            coroutineContextProvider = coroutineContextProvider,
            contactRepository = contactRepository,
        )
    }

    val deleteContactUseCase by lazy {
        DeleteContactUseCase(
            coroutineContextProvider = coroutineContextProvider,
            contactRepository = contactRepository,
        )
    }

    val contactViewModel by lazy {
        ContactViewModel(
            contactListDomainToPresentationMapper = contactListDomainToPresentationMapper,
            useCaseExecutor = useCaseExecutor,
            getAllContactsUseCase = getAllContactsUseCase,
            deleteContactUseCase = deleteContactUseCase,
            addContactUseCase = addContactUseCase,

        )
    }
}
