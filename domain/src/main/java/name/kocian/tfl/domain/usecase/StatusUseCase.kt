package name.kocian.tfl.domain.usecase

import io.reactivex.Single
import name.kocian.tfl.domain.entity.LineStatus
import name.kocian.tfl.domain.repository.StatusRepository
import javax.inject.Inject

class StatusUseCase @Inject constructor(
        private val repository: StatusRepository
) : UseCase<Single<List<LineStatus>>> {

    override fun initialise(): Single<List<LineStatus>> {
        return repository.getLineStatus()
    }
}
