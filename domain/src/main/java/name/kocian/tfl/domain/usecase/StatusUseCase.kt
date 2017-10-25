package name.kocian.tfl.domain.usecase

import io.reactivex.Scheduler
import io.reactivex.Single
import name.kocian.tfl.domain.base.BaseUseCase.Companion.SCHEDULER_RESULT
import name.kocian.tfl.domain.base.BaseUseCase.Companion.SCHEDULER_WORKER
import name.kocian.tfl.domain.base.SingleUseCase
import name.kocian.tfl.domain.entity.LineStatus
import name.kocian.tfl.domain.repository.StatusRepository
import javax.inject.Inject
import javax.inject.Named

class StatusUseCase @Inject constructor(
        private val repository: StatusRepository,
        @Named(SCHEDULER_WORKER) processScheduler: Scheduler,
        @Named(SCHEDULER_RESULT) resultScheduler: Scheduler
) : SingleUseCase<List<LineStatus>>(processScheduler, resultScheduler) {

    override fun build(): Single<List<LineStatus>> {
        return repository.getLineStatus()
    }
}
