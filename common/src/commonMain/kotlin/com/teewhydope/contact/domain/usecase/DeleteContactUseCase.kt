package com.teewhydope.contact.domain.usecase

import com.teewhydope.architecture.domain.BackgroundExecutingUseCase
import com.teewhydope.architecture.domain.UseCase
import com.teewhydope.contact.domain.repository.ContactRepository
import com.teewhydope.coroutine.CoroutineContextProvider

interface DeleteContactUseCase : UseCase<Long, Unit>

class DeleteContactUseCaseImpl(
    private val contactRepository: ContactRepository,
    coroutineContextProvider: CoroutineContextProvider,
) : DeleteContactUseCase, BackgroundExecutingUseCase<Long, Unit>(coroutineContextProvider) {
    override fun executeInBackground(request: Long) = contactRepository.delete(request)
}
