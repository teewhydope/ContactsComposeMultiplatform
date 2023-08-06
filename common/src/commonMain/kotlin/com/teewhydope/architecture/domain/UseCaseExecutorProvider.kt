package com.teewhydope.architecture.domain

import kotlinx.coroutines.CoroutineScope
import kotlin.jvm.JvmSuppressWildcards

typealias UseCaseExecutorProvider =
@JvmSuppressWildcards (coroutineScope: CoroutineScope) -> UseCaseExecutor
