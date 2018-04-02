package name.kocian.tfl.presentation.ui.sample

import name.kocian.tfl.presentation.model.StatusModel
import name.kocian.tfl.presentation.mvp.MvpPresenter
import name.kocian.tfl.presentation.mvp.MvpView

interface LineStatusMvp {

    interface View : MvpView {
        fun showStatuses(statuses: List<StatusModel>)

        fun showStatusNotAvailableError()
        fun hideStatusNotAvailableError()

        fun hideLoading()

        fun showNoNetworkMessage()
        fun hideNoNetworkMessage()
    }

    interface Presenter : MvpPresenter<View> {
        fun initPresenter()

        fun loadStatus()
    }
}
