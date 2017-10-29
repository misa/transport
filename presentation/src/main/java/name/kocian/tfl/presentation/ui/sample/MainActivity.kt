package name.kocian.tfl.presentation.ui.sample

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import name.kocian.tfl.R
import name.kocian.tfl.presentation.model.StatusModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SampleMvp.View {

    @Inject
    lateinit var presenter: SampleMvp.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        presenter.attachView(this)
        presenter.initPresenter()
        status_swipe_refresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { presenter.loadStatus() })
    }

    override fun showStatuses(statuses: List<StatusModel>) {
        val adapter = LineStatusAdapter()
        adapter.items = statuses
        rv_lines.adapter = adapter
        rv_lines.layoutManager = LinearLayoutManager(this)
    }

    override fun showStatusNotAvailableError() {
        status_error_message.setText(R.string.error_data_not_available)
    }

    override fun hideStatusNotAvailableError() {
        status_error_message.visibility = View.GONE
    }

    override fun hideLoading() {
        status_swipe_refresh.isRefreshing = false
    }
}
