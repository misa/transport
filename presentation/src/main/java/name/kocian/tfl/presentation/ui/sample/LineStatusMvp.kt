package name.kocian.tfl.presentation.ui.sample

import io.reactivex.Observable
import name.kocian.tfl.presentation.model.StatusModel
import name.kocian.tfl.presentation.mvp.MvpPresenter
import name.kocian.tfl.presentation.mvp.MvpView

interface LineStatusMvp {

    interface View : MvpView {
        fun showStatuses(statuses: List<StatusModel>)

        fun showStatusNotAvailableError()
        fun hideStatusNotAvailableError()

        fun hideLoading()
        fun showLoading()

        fun showNoNetworkMessage()
        fun hideNoNetworkMessage()

        fun reloadStatuses(): Observable<Any>
    }

    interface Presenter : MvpPresenter<View> {
        fun initPresenter()

        fun loadStatuses()
    }
}
