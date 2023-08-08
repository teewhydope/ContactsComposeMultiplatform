package com.teewhydope.contact.domain.usecase

import com.teewhydope.architecture.domain.ContinuousExecutingUseCase
import com.teewhydope.architecture.domain.UseCase
import com.teewhydope.contact.domain.model.ContactListDomainModel
import com.teewhydope.contact.domain.repository.ContactRepository
import com.teewhydope.coroutine.CoroutineContextProvider
import kotlinx.coroutines.flow.collectLatest

interface GetContactsUseCase : UseCase<Long, ContactListDomainModel>

class GetContactsUseCaseImpl(
    private val contactRepository: ContactRepository,
    coroutineContextProvider: CoroutineContextProvider,
) : GetContactsUseCase,
    ContinuousExecutingUseCase<Long, ContactListDomainModel>(coroutineContextProvider) {
    override suspend fun executeInBackground(
        request: Long,
        onResult: (ContactListDomainModel) -> Unit,
    ) = contactRepository.contacts(request).collectLatest { onResult(it) }
}
