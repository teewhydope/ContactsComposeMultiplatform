package com.teewhydope.contact.domain.usecase

import com.teewhydope.architecture.domain.ContinuousExecutingUseCase
import com.teewhydope.contact.domain.model.ContactListDomainModel
import com.teewhydope.contact.domain.repository.ContactRepository
import com.teewhydope.coroutine.CoroutineContextProvider
import kotlinx.coroutines.flow.collectLatest

class GetRecentContactsUseCase(
    private val contactRepository: ContactRepository,
    coroutineContextProvider: CoroutineContextProvider,
) : ContinuousExecutingUseCase<Int, ContactListDomainModel>(coroutineContextProvider) {
    override suspend fun executeInBackground(
        request: Int,
        callback: (ContactListDomainModel) -> Unit,
    ) = contactRepository.recentContacts(request).collectLatest { callback(it) }
}
