package name.kocian.tfl.domain.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import name.kocian.tfl.domain.repository.SampleRepository
import javax.inject.Inject
import javax.inject.Named

class SampleUseCase @Inject constructor(
        private val repository: SampleRepository,
        @Named(SCHEDULER_WORKER) processScheduler: Scheduler,
        @Named(SCHEDULER_RESULT) resultScheduler: Scheduler
) : AbstractUseCase<String>(processScheduler, resultScheduler) {

    override fun buildObservable(): Observable<String> {
        return repository.getTest()
    }
}
