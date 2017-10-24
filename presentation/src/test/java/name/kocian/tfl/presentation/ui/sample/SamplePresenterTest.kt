package name.kocian.tfl.presentation.ui.sample

import io.reactivex.Observable
import name.kocian.tfl.device.network.NetworkManager
import name.kocian.tfl.domain.usecase.SampleUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SamplePresenterTest {
    private lateinit var presenter: SampleMvp.Presenter

    @Mock
    private lateinit var mockSampleUseCase: SampleUseCase

    @Mock
    private lateinit var mockView: SampleMvp.View

    @Mock
    private lateinit var mockNetworkManager: NetworkManager

    @Before
    fun setUp() {
        presenter = SamplePresenter(mockSampleUseCase, mockNetworkManager)
        presenter.attachView(mockView)
    }

    @Test
    fun initPresenterShowsWelcomeText() {
        val text = "test"
        `when`(mockSampleUseCase.asObservable()).thenReturn(Observable.just(text))
        `when`(mockNetworkManager.networkChangesObservable()).thenReturn(Observable.just(true))

        presenter.initPresenter()

        verify(mockView).showGreetings(text)
    }
}
