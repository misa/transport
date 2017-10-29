package name.kocian.tfl.presentation.ui.sample

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import name.kocian.tfl.device.network.NetworkManager
import name.kocian.tfl.domain.usecase.StatusUseCase
import name.kocian.tfl.presentation.mvp.BasePresenter

class LineStatusPresenter(
        private val statusUseCase: StatusUseCase,
        private val networkManager: NetworkManager)
    : LineStatusMvp.Presenter, BasePresenter<LineStatusMvp.View>() {

    override fun initPresenter() {
        loadStatus()

        attachNetworkManager(networkManager)
    }

    override fun loadStatus() {

        compositeDisposable.add(statusUseCase.build()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .flatMap { Observable.fromIterable(it) }
                .sorted(LinesComparator())
                .map { LineStatusModelMapping().toModel(it) }
                .toList()
                .subscribe(
                        {
                            view?.hideStatusNotAvailableError()
                            view?.hideLoading()
                            view?.showStatuses(it)
                        },
                        {
                            view?.hideLoading()
                            view?.showStatusNotAvailableError()
                        }
                ))
    }

    override fun onNetworkReconnected() {
        Log.v("LineStatusPresenter", "Network reconnected")
    }

    override fun onNetworkDisconnected() {
        Log.v("LineStatusPresenter", "Network disconnected")
    }
}
