package name.kocian.tfl.presentation.ui.sample

import android.util.Log
import name.kocian.tfl.device.network.NetworkManager
import name.kocian.tfl.domain.usecase.SampleUseCase
import name.kocian.tfl.presentation.mvp.BasePresenter

class SamplePresenter(
        private val sampleUseCase: SampleUseCase,
        private val networkManager: NetworkManager)
    : SampleMvp.Presenter, BasePresenter<SampleMvp.View>() {

    override fun initPresenter() {
        compositeDisposable.add(sampleUseCase.asObservable()
                .subscribe({ view?.showGreetings(it) }))

        attachNetworkManager(networkManager)
    }

    override fun onNetworkReconnected() {
        Log.v("SamplePresenter", "Network reconnected")
    }

    override fun onNetworkDisconnected() {
        Log.v("SamplePresenter", "Network disconnected")
    }
}
