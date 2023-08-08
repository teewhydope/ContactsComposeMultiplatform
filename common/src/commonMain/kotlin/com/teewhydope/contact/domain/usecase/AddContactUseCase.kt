package com.teewhydope.contact.domain.usecase

import com.teewhydope.architecture.domain.BackgroundExecutingUseCase
import com.teewhydope.architecture.domain.UseCase
import com.teewhydope.contact.domain.model.ContactDomainModel
import com.teewhydope.contact.domain.repository.ContactRepository
import com.teewhydope.coroutine.CoroutineContextProvider

interface AddContactUseCase : UseCase<ContactDomainModel, Unit>

class AddContactUseCaseImpl(
    private val contactRepository: ContactRepository,
    coroutineContextProvider: CoroutineContextProvider,
) : AddContactUseCase,
    BackgroundExecutingUseCase<ContactDomainModel, Unit>(coroutineContextProvider) {
    override fun executeInBackground(request: ContactDomainModel) =
        contactRepository.insert(request)
}
