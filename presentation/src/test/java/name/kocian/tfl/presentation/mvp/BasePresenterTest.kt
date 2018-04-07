package name.kocian.tfl.presentation.mvp

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BasePresenterTest {
    private lateinit var presenter: BasePresenter<MvpView>

    @Mock
    private lateinit var mockView: MvpView

    @Before
    fun setUp() {
        presenter = BasePresenter()
    }

    @Test
    fun preconditions() {
        assertThat(presenter).extracting("view").contains(null)
    }

    @Test
    fun attachView() {
        presenter.attachView(mockView)

        assertThat(presenter).extracting("view").contains(mockView)
    }

    @Test
    fun detachView() {
        presenter.attachView(mockView)
        presenter.detachView()

        assertThat(presenter).extracting("view").contains(null)
    }
}
