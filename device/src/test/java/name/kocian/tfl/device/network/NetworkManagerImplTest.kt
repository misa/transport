package name.kocian.tfl.device.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@Suppress("FunctionName")
@RunWith(MockitoJUnitRunner::class)
class NetworkManagerImplTest {
    private lateinit var networkManager: NetworkManager

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockConnectivityManager: ConnectivityManager

    @Mock
    private lateinit var mockNetworkInfo: NetworkInfo

    @Before
    fun setUp() {
        networkManager = NetworkManagerImpl(mockContext)
    }

    @Test
    fun isNetworkConnected_returnsTrue() {
        `when`(mockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mockConnectivityManager)
        `when`(mockConnectivityManager.activeNetworkInfo).thenReturn(mockNetworkInfo)
        `when`(mockNetworkInfo.isConnectedOrConnecting).thenReturn(true)

        assertThat(networkManager.isConnectionEstablished()).isTrue()
    }
}
