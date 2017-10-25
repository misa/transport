package name.kocian.tfl.domain.base

interface BaseUseCase {

    companion object {
        const val SCHEDULER_WORKER = "workerScheduler"
        const val SCHEDULER_RESULT = "resultScheduler"
    }

    fun build()
}
