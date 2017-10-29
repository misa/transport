package name.kocian.tfl.datasource.repository

import io.reactivex.Observable
import io.reactivex.Single
import name.kocian.tfl.datasource.dto.LineStatusDtoMapper
import name.kocian.tfl.datasource.service.StatusService
import name.kocian.tfl.domain.entity.LineStatus
import name.kocian.tfl.domain.repository.StatusRepository
import javax.inject.Inject

class StatusRepositoryImpl @Inject constructor(
        private val statusService: StatusService,
        private val mapper: LineStatusDtoMapper) : StatusRepository {

    override fun getLineStatus(): Single<List<LineStatus>> {
        return statusService.lineStatus
                .toObservable()
                .flatMap { t -> Observable.fromIterable(t) }
                .map { mapper.toDomain(it) }
                .toList()
    }
}
