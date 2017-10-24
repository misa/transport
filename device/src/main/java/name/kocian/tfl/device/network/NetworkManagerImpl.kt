package name.kocian.tfl.device.network

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.cantrowitz.rxbroadcast.RxBroadcast
import io.reactivex.Observable
import javax.inject.Inject

class NetworkManagerImpl @Inject constructor(private val context: Context) : NetworkManager {

    override fun isConnectionEstablished(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    override fun networkChangesObservable(): Observable<Boolean> {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)

        return RxBroadcast.fromBroadcast(context, filter)
                .map { _ -> isConnectionEstablished() }
                .distinctUntilChanged()
    }
}
