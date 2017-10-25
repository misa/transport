package name.kocian.tfl.domain.base

import io.reactivex.Scheduler
import name.kocian.tfl.domain.base.BaseUseCase.Companion.SCHEDULER_RESULT
import name.kocian.tfl.domain.base.BaseUseCase.Companion.SCHEDULER_WORKER
import javax.inject.Named

abstract class AbstractUseCase<T>(
        @Named(SCHEDULER_WORKER) processScheduler: Scheduler,
        @Named(SCHEDULER_RESULT) resultScheduler: Scheduler)
