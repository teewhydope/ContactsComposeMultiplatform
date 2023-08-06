package com.teewhydope.contact.domain.usecase

import com.teewhydope.architecture.domain.BackgroundExecutingUseCase
import com.teewhydope.contact.domain.repository.ContactRepository
import com.teewhydope.coroutine.CoroutineContextProvider

class DeleteContactUseCase(
    private val contactRepository: ContactRepository,
    coroutineContextProvider: CoroutineContextProvider,
) : BackgroundExecutingUseCase<Long, Unit>(coroutineContextProvider) {
    override fun executeInBackground(request: Long) = contactRepository.delete(request)
}
