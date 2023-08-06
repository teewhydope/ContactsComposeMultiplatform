package com.teewhydope.architecture.data.mapper

import com.teewhydope.contact.datasource.source.remote.RequestTimeoutDataException
import com.teewhydope.architecture.domain.exception.DomainException
import com.teewhydope.architecture.domain.exception.UnknownDomainException
import com.teewhydope.contact.domain.exception.ReadFailedDomainException

class ThrowableToDomainMapper {
    fun toDomain(exception: Throwable): DomainException =
        when (exception) {
            is RequestTimeoutDataException -> ReadFailedDomainException(exception)
            else -> UnknownDomainException(exception)
        }
}
