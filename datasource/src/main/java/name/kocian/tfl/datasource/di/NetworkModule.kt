package name.kocian.tfl.datasource.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import name.kocian.tfl.datasource.BuildConfig
import name.kocian.tfl.datasource.service.StatusService
import name.kocian.tfl.device.network.NetworkManager
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    companion object {
        var baseUrl = BuildConfig.BASE_URL
    }

    @Provides
    fun provideProductService(retrofit: Retrofit): StatusService {
        return retrofit.create(StatusService::class.java)
    }

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }

    @Provides
    fun provideHttpClient(cacheInterceptor: CacheInterceptor, cache: Cache): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(cacheInterceptor)

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        return builder
                .connectTimeout(BuildConfig.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(BuildConfig.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(BuildConfig.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    fun provideCacheInterceptor(networkManager: NetworkManager): CacheInterceptor {
        return CacheInterceptor(networkManager)
    }

    @Provides
    fun provideNetworkCache(context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir, BuildConfig.CACHE_DIRECTORY)
        return Cache(httpCacheDirectory, BuildConfig.CACHE_SIZE)
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}
