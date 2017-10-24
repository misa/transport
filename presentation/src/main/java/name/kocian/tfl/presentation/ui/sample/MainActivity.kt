package name.kocian.tfl.presentation.ui.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import name.kocian.tfl.R
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
    }

    override fun showGreetings(message: String) {
        welcome.text = message
    }
}
