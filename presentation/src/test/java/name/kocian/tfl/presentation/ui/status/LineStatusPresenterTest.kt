package name.kocian.tfl.presentation.ui.status

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import name.kocian.tfl.device.network.NetworkManager
import name.kocian.tfl.domain.entity.LineStatus
import name.kocian.tfl.domain.usecase.StatusUseCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.net.UnknownHostException
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class LineStatusPresenterTest {

    private lateinit var presenter: LineStatusMvp.Presenter

    @Mock
    private lateinit var mockStatusUseCase: StatusUseCase

    @Mock
    private lateinit var mockView: LineStatusMvp.View

    @Mock
    private lateinit var mockNetworkManager: NetworkManager

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        presenter = LineStatusPresenter(mockStatusUseCase, mockNetworkManager)
        presenter.attachView(mockView)
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun initPresenterShowsListOfStatuses() {
        val result: List<LineStatus> = Collections.emptyList()
        initialise(Single.just(result), Observable.empty(), Observable.just(true))

        presenter.initPresenter()

        verify(mockView).showStatuses(anyList())
        verify(mockView).hideStatusNotAvailableError()
        verify(mockView).hideLoading()
    }

    @Test
    fun initPresenterShowsCachedDataWhenOffline() {
        val result: List<LineStatus> = Collections.emptyList()
        initialise(Single.just(result), Observable.empty(), Observable.just(false))

        presenter.initPresenter()

        verify(mockView).showStatuses(anyList())
        verify(mockView).hideStatusNotAvailableError()
        verify(mockView).hideLoading()
    }

    @Test
    fun initPresenterShowsErrorMessage() {
        val result = UnknownHostException()
        initialise(Single.error(result), Observable.empty(), Observable.just(true))

        presenter.initPresenter()

        verify(mockView).showStatusNotAvailableError()
        verify(mockView).hideLoading()
        verify(mockView, never()).showStatuses(anyList())
    }

    private fun initialise(
            statusResult: Single<List<LineStatus>>,
            reloadStatus: Observable<Any>,
            networkStatus: Observable<Boolean>) {

        `when`(mockStatusUseCase.initialise()).thenReturn(statusResult)
        `when`(mockView.reloadStatuses()).thenReturn(reloadStatus)
        `when`(mockNetworkManager.networkChangesObservable()).thenReturn(networkStatus)
    }
}
