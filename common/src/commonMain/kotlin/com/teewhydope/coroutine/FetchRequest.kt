package com.teewhydope.coroutine

import com.teewhydope.app.di.Graph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
Original Link
https://github.com/alexstyl/contactstore/blob/ee2c5b0bc014b8191783e44e610315c1cc51d896/library/src/main/java/com/alexstyl/contactstore/FetchRequest.kt#L12
 **/
public class FetchRequest<T>(
    private val flow: Flow<T>,
    dispatcher: CoroutineContextProvider = Graph.coroutineContextProvider,
) {
    private val scope = CoroutineScope(dispatcher.io + Job())

    /**
     * Returns the values of the given request in a blocking manner.
     */
    public fun blockingGet(): T {
        return runBlocking { flow.first() }
    }

    /**
     * Accepts the given collector and emits values into it.
     *
     * The collector will continue receiving new values once a change is detected (i.e. an other app adds a new contact or a Content Provider syncs a new account) and never stops.
     *
     * Make sure to call [FetchJob.cancel] to clear the job and prevent any memory leaks.
     */
    public fun collect(collector: (T) -> Unit): FetchJob {
        val job = scope.launch {
            flow.collect {
                collector(it)
            }
        }
        return FetchJob(job)
    }

    public fun collectLatest(collector: (T) -> Unit): FetchJob {
        val job = scope.launch {
            flow.collectLatest {
                collector(it)
            }
        }
        return FetchJob(job)
    }
}

/**
 * Represents an ongoing fetching request.
 */
public class FetchJob(
    private val coroutineJob: Job,
) {
    public val isCancelled: Boolean
        get() = coroutineJob.isCancelled

    public fun cancel() {
        coroutineJob.cancel()
    }
}
