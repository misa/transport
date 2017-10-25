package name.kocian.tfl.domain.base

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class SingleUseCase<T>(
        processScheduler: Scheduler,
        resultScheduler: Scheduler)
    : AbstractUseCase<T>(processScheduler, resultScheduler) {

    abstract fun build(): Single<T>
}
