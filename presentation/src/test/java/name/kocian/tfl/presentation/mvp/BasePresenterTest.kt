package name.kocian.tfl.presentation.mvp

import io.reactivex.Observable
import name.kocian.tfl.device.network.NetworkManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@Suppress("FunctionName")
@RunWith(MockitoJUnitRunner::class)
class BasePresenterTest {
    private lateinit var presenter: BasePresenter<MvpView>

    @Mock
    private lateinit var mockNetworkManager: NetworkManager

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
    fun attachView_attachesView() {
        presenter.attachView(mockView)

        assertThat(presenter).extracting("view").contains(mockView)
    }

    @Test
    fun detachView_detachesView() {
        presenter.attachView(mockView)
        presenter.detachView()

        assertThat(presenter).extracting("view").contains(null)
    }

    @Test
    fun networkStatus_initialStateIsIgnored() {
        val spyPresenter = spy(presenter)

        val networkStateObservable = Observable.just(true)
        `when`(mockNetworkManager.networkChangesObservable()).thenReturn(networkStateObservable)

        spyPresenter.attachNetworkManager(mockNetworkManager)

        verify(spyPresenter, never()).onNetworkDisconnected()
        verify(spyPresenter, never()).onNetworkReconnected()
    }

    @Test
    fun networkStatus_stateIgnoredAndDisconnectedAndConnected() {
        val spyPresenter = spy(presenter)
        val inOrder = inOrder(spyPresenter)

        val networkStateObservable = Observable.just(true, false, true)
        `when`(mockNetworkManager.networkChangesObservable()).thenReturn(networkStateObservable)

        spyPresenter.attachNetworkManager(mockNetworkManager)

        inOrder.verify(spyPresenter).onNetworkDisconnected()
        inOrder.verify(spyPresenter).onNetworkReconnected()
    }

    @Test
    fun networkStatus_stateIgnoredAndConnectedAndDisconnected() {
        val spyPresenter = spy(presenter)
        val inOrder = inOrder(spyPresenter)

        val networkStateObservable = Observable.just(true, true, false)
        `when`(mockNetworkManager.networkChangesObservable()).thenReturn(networkStateObservable)

        spyPresenter.attachNetworkManager(mockNetworkManager)

        inOrder.verify(spyPresenter).onNetworkReconnected()
        inOrder.verify(spyPresenter).onNetworkDisconnected()
    }
}
