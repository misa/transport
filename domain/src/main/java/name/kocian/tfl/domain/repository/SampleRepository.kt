package name.kocian.tfl.domain.repository

import io.reactivex.Observable

interface SampleRepository: Repository {

    fun getTest(): Observable<String>
}
