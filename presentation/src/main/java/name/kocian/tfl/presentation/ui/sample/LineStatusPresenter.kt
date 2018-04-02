package name.kocian.tfl.presentation.ui.sample

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
        loadStatuses()
        initReloading()
        initNetworkChanges()
    }

    override fun loadStatuses() {
        view?.showLoading()
        compositeDisposable.add(statusUseCase.initialise()
                .map {
                    it.sortedWith(LinesComparator())
                            .map { LineStatusModelMapping().toModel(it) }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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

    private fun initReloading() {
        compositeDisposable.add(view!!.reloadStatuses()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loadStatuses()
                }, {
                    view?.hideLoading()
                }))
    }

    private fun initNetworkChanges() {
        compositeDisposable.add(networkManager
                .networkChangesObservable()
                .distinctUntilChanged()
                .onErrorReturn { true }
                .subscribe({
                    if (it) {
                        view?.hideNoNetworkMessage()
                        loadStatuses()
                    } else {
                        view?.showNoNetworkMessage()
                    }
                }, {
                    it.printStackTrace()
                }))
    }
}
