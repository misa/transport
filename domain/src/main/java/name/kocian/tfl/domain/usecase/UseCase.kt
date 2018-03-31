package name.kocian.tfl.domain.usecase

interface UseCase<out T> {
    fun initialise(): T
}
