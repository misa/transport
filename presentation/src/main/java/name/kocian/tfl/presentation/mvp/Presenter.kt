package name.kocian.tfl.presentation.mvp

import io.reactivex.disposables.CompositeDisposable

interface MvpPresenter<in T> {
    fun attachView(view: T)
    fun detachView()
}

open class BasePresenter<T> : MvpPresenter<T> {
    var view: T? = null
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        view = null
        compositeDisposable.clear()
    }
}
