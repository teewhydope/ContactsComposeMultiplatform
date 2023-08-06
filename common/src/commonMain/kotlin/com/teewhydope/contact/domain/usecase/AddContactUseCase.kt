package com.teewhydope.contact.domain.usecase

import com.teewhydope.architecture.domain.BackgroundExecutingUseCase
import com.teewhydope.contact.domain.model.ContactDomainModel
import com.teewhydope.contact.domain.repository.ContactRepository
import com.teewhydope.coroutine.CoroutineContextProvider

class AddContactUseCase(
    private val contactRepository: ContactRepository,
    coroutineContextProvider: CoroutineContextProvider,
) : BackgroundExecutingUseCase<ContactDomainModel, Unit>(coroutineContextProvider) {
    override fun executeInBackground(request: ContactDomainModel) =
        contactRepository.insert(request)
}
