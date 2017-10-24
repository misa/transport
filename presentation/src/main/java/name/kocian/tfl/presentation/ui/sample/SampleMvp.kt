package name.kocian.tfl.presentation.ui.sample

import name.kocian.tfl.presentation.mvp.MvpPresenter
import name.kocian.tfl.presentation.mvp.MvpView

interface SampleMvp {

    interface View : MvpView {
        fun showGreetings(message: String)
    }

    interface Presenter : MvpPresenter<View> {
        fun initPresenter()
    }
}
