package name.kocian.tfl.presentation.ui.sample

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
    }

    override fun loadStatus() {

        compositeDisposable.add(statusUseCase.initialise()
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

        compositeDisposable.add(networkManager
                .networkChangesObservable()
                .distinctUntilChanged()
                .onErrorReturn { true }
                .subscribe({
                    if (it) {
                        view?.hideNoNetworkMessage()
                    } else {
                        view?.showNoNetworkMessage()
                    }
                }))
    }
}
