package com.teewhydope.contact.domain.usecase

import com.teewhydope.architecture.domain.ContinuousExecutingUseCase
import com.teewhydope.contact.domain.model.ContactListDomainModel
import com.teewhydope.contact.domain.repository.ContactRepository
import com.teewhydope.coroutine.CoroutineContextProvider
import kotlinx.coroutines.flow.collectLatest

class GetAllContactsUseCase(
    private val contactRepository: ContactRepository,
    coroutineContextProvider: CoroutineContextProvider,
) : ContinuousExecutingUseCase<Unit, ContactListDomainModel>(coroutineContextProvider) {
    override suspend fun executeInBackground(
        request: Unit,
        callback: (ContactListDomainModel) -> Unit,
    ) = contactRepository.allContacts().collectLatest { callback(it) }
}
