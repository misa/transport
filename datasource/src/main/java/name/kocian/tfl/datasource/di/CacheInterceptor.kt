package name.kocian.tfl.datasource.di

import name.kocian.tfl.datasource.BuildConfig
import name.kocian.tfl.device.network.NetworkManager
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor(private val networkManager: NetworkManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val cacheHeaderValue: String

        if (networkManager.isConnectionEstablished()) {
            cacheHeaderValue = "max-age=" + BuildConfig.CACHE_MAX_AGE
        } else {
            cacheHeaderValue = "only-if-cached, max-stale=" + BuildConfig.CACHE_MAX_STALE
        }

        val request = chain.request().newBuilder()
                .cacheControl(CacheControl.Builder()
                        .maxStale(BuildConfig.CACHE_MAX_STALE, TimeUnit.SECONDS)
                        .build())
                .build()
        val response = chain.proceed(request)

        return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, $cacheHeaderValue")
                .build()
    }
}
