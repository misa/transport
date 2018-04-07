package name.kocian.tfl.datasource.di

import name.kocian.tfl.datasource.BuildConfig
import name.kocian.tfl.device.network.NetworkManager
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class OfflineCacheInterceptor(private val networkManager: NetworkManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (!networkManager.isConnectionEstablished()) {
            val staleCache = CacheControl.Builder()
                    .maxStale(BuildConfig.CACHE_MAX_STALE, TimeUnit.SECONDS)
                    .build()
            request = request.newBuilder()
                    .cacheControl(staleCache)
                    .build()
        }

        return chain.proceed(request)
    }
}
