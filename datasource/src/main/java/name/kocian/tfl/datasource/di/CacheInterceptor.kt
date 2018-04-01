package name.kocian.tfl.datasource.di

import android.content.Context
import android.net.ConnectivityManager
import name.kocian.tfl.datasource.BuildConfig
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val cacheHeaderValue: String

        if (isNetworkConnected(context)) {
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
                .header("Cache-Control", "public, " + cacheHeaderValue)
                .build()
    }

    private fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}
