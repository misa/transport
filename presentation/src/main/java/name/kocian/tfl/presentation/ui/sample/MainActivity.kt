package name.kocian.tfl.presentation.ui.sample

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import dagger.android.AndroidInjection
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import name.kocian.tfl.R
import name.kocian.tfl.presentation.model.StatusModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), LineStatusMvp.View {

    @Inject
    lateinit var presenter: LineStatusMvp.Presenter

    private val adapter = LineStatusAdapter()
    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        presenter.attachView(this)
        presenter.initPresenter()
    }

    override fun reloadStatuses(): Observable<Any> {
        return RxSwipeRefreshLayout.refreshes(statusSwipeRefresh)
    }

    override fun showStatuses(statuses: List<StatusModel>) {
        if (statusesRecyclerView.layoutManager == null) {
            statusesRecyclerView.layoutManager = LinearLayoutManager(this)
            statusesRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            statusesRecyclerView.adapter = adapter
        }

        adapter.setStatuses(statuses)
    }

    override fun showStatusNotAvailableError() {
        errorMessageTextView.setText(R.string.error_data_not_available)
    }

    override fun hideStatusNotAvailableError() {
        errorMessageTextView.visibility = View.GONE
    }

    override fun showNoNetworkMessage() {
        if (snackbar == null) {
            snackbar = Snackbar.make(coordinatorLayout, R.string.error_no_network, Snackbar.LENGTH_INDEFINITE)
        }
        snackbar?.show()
    }

    override fun hideNoNetworkMessage() {
        snackbar?.dismiss()
    }

    override fun hideLoading() {
        statusSwipeRefresh.isRefreshing = false
    }

    override fun showLoading() {
        statusSwipeRefresh.isRefreshing = true
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
