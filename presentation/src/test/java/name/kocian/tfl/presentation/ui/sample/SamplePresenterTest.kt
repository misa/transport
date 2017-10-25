package name.kocian.tfl.presentation.ui.sample

import name.kocian.tfl.device.network.NetworkManager
import name.kocian.tfl.domain.usecase.StatusUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SamplePresenterTest {
    private lateinit var presenter: SampleMvp.Presenter

    @Mock
    private lateinit var mockStatusUseCase: StatusUseCase

    @Mock
    private lateinit var mockView: SampleMvp.View

    @Mock
    private lateinit var mockNetworkManager: NetworkManager

    @Before
    fun setUp() {
        presenter = SamplePresenter(mockStatusUseCase, mockNetworkManager)
        presenter.attachView(mockView)
    }

    @Test
    fun initPresenterShowsWelcomeText() {
    }
}
