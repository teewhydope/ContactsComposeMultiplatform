package com.teewhydope.architecture.domain

import com.teewhydope.architecture.domain.exception.DomainException
import com.teewhydope.architecture.domain.exception.UnknownDomainException

interface UseCase<REQUEST, RESULT> {
    suspend fun execute(input: REQUEST, onResult: (RESULT) -> Unit)

    fun onError(throwable: Throwable): DomainException = UnknownDomainException(throwable)
}
