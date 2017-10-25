package name.kocian.tfl.domain.repository

import io.reactivex.Single
import name.kocian.tfl.domain.entity.LineStatus

interface StatusRepository : Repository {

    fun getLineStatus(): Single<List<LineStatus>>
}
