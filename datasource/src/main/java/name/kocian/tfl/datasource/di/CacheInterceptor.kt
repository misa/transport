package name.kocian.tfl.datasource.di

import name.kocian.tfl.datasource.BuildConfig
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
                .maxAge(BuildConfig.CACHE_MAX_AGE, TimeUnit.SECONDS)
                .build()

        return response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
    }
}
