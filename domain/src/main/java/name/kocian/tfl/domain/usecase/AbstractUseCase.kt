package name.kocian.tfl.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler

abstract class AbstractUseCase<T>(
        val processScheduler: Scheduler,
        val resultScheduler: Scheduler
) : UseCase<T> {

    companion object {
        const val SCHEDULER_WORKER = "workerScheduler"
        const val SCHEDULER_RESULT = "resultScheduler"
    }

    /**
     * Builds an [Observable] which will be used when executing a Use Case implementation.
     */
    protected abstract fun buildObservable(): Observable<T>

    /**
     * Get a prepared Use case.
     *
     * @return Prepared Use case observable
     */
    override fun asObservable(): Observable<T> {
        return buildObservable()
                .subscribeOn(processScheduler)
                .observeOn(resultScheduler)
    }
}
