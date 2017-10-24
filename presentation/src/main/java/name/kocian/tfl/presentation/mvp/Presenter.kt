package name.kocian.tfl.presentation.mvp

import io.reactivex.disposables.CompositeDisposable
import name.kocian.tfl.device.network.NetworkManager

interface MvpPresenter<in T> {
    fun attachView(view: T)

    fun detachView()

    fun attachNetworkManager(networkManager: NetworkManager)

    fun onNetworkReconnected()

    fun onNetworkDisconnected()
}

open class BasePresenter<T> : MvpPresenter<T> {
    var view: T? = null
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var networkStatus: Boolean? = null

    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        view = null
        compositeDisposable.clear()
    }

    override fun attachNetworkManager(networkManager: NetworkManager) {
        compositeDisposable.add(networkManager
                .networkChangesObservable()
                .subscribe({ handleNetworkStatus(it) }))
    }

    private fun handleNetworkStatus(status: Boolean) {
        if (networkStatus == null) {
            networkStatus = status
        } else {
            val previousState = networkStatus
            networkStatus = status
            if (previousState != null && networkStatus != null && networkStatus == true) {
                onNetworkReconnected()
            } else if (previousState == true && networkStatus == false) {
                onNetworkDisconnected()
            }
        }
    }

    override fun onNetworkReconnected() {
        // No-op
    }

    override fun onNetworkDisconnected() {
        // No-op
    }
}
