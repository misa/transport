package name.kocian.tfl.presentation.ui.sample

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import name.kocian.tfl.device.network.NetworkManager
import name.kocian.tfl.domain.usecase.StatusUseCase
import name.kocian.tfl.presentation.mvp.BasePresenter

class SamplePresenter(
        private val statusUseCase: StatusUseCase,
        private val networkManager: NetworkManager)
    : SampleMvp.Presenter, BasePresenter<SampleMvp.View>() {

    override fun initPresenter() {
        loadStatus()

        attachNetworkManager(networkManager)
    }

    override fun loadStatus() {
        compositeDisposable.add(statusUseCase.build()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view?.hideStatusNotAvailableError()
                            view?.showGreetings(it.get(0).name)
                        },
                        {
                            view?.hideLoading()
                            view?.showStatusNotAvailableError()
                        }
                ))
    }

    override fun onNetworkReconnected() {
        Log.v("SamplePresenter", "Network reconnected")
    }

    override fun onNetworkDisconnected() {
        Log.v("SamplePresenter", "Network disconnected")
    }
}
