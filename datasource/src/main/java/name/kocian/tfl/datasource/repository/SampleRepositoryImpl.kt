package name.kocian.tfl.datasource.repository

import io.reactivex.Observable
import name.kocian.tfl.datasource.service.SampleService
import name.kocian.tfl.domain.repository.SampleRepository
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(
        private val sampleService: SampleService) : SampleRepository {

    override fun getTest(): Observable<String> {
        return sampleService.lineStatus
                .map { t -> t.joinToString(separator = "\n") { it.name } }
    }
}
