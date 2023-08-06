package com.teewhydope.contact.domain.exception

import com.teewhydope.architecture.domain.exception.DomainException

class ReadFailedDomainException(throwable: Throwable) : DomainException(throwable)
