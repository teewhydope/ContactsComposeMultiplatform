package com.teewhydope.contact.domain.usecase

import com.teewhydope.architecture.domain.ContinuousExecutingUseCase
import com.teewhydope.architecture.domain.UseCase
import com.teewhydope.contact.domain.model.ContactListDomainModel
import com.teewhydope.contact.domain.repository.ContactRepository
import com.teewhydope.coroutine.CoroutineContextProvider
import kotlinx.coroutines.flow.collectLatest

interface GetContactsUseCase : UseCase<Unit, ContactListDomainModel>

class GetContactsUseCaseImpl(
    private val contactRepository: ContactRepository,
    coroutineContextProvider: CoroutineContextProvider,
) : GetContactsUseCase,
    ContinuousExecutingUseCase<Unit, ContactListDomainModel>(coroutineContextProvider) {
    override suspend fun executeInBackground(
        request: Unit,
        onResult: (ContactListDomainModel) -> Unit,
    ) = contactRepository.contacts().collectLatest { onResult(it) }
}
