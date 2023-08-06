package com.teewhydope.architecture.domain

import com.teewhydope.coroutine.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class ContinuousExecutingUseCase<REQUEST, RESULT> constructor(
    private val coroutineContextProvider: CoroutineContextProvider,
) : UseCase<REQUEST, RESULT> {
    final override suspend fun execute(input: REQUEST, callback: (RESULT) -> Unit) {
        withContext(coroutineContextProvider.io) {
            executeInBackground(input) { result ->
                CoroutineScope(coroutineContextProvider.main).launch {
                    callback(result)
                }
            }
        }
    }

    abstract suspend fun executeInBackground(
        request: REQUEST,
        callback: (RESULT) -> Unit,
    )

    open fun cleanup() {}
}
