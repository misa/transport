package name.kocian.tfl.domain.usecase

import io.reactivex.Observable

interface UseCase<T> {
    fun asObservable(): Observable<T>
}
