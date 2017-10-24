package name.kocian.tfl.datasource.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import name.kocian.tfl.datasource.BuildConfig
import name.kocian.tfl.datasource.service.SampleService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    companion object {
        var baseUrl = BuildConfig.BASE_URL
    }

    @Provides
    fun provideProductService(retrofit: Retrofit): SampleService {
        return retrofit.create(SampleService::class.java)
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
    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        return builder
                .connectTimeout(BuildConfig.NETWORK_TIMEOUT, TimeUnit.MINUTES)
                .readTimeout(BuildConfig.NETWORK_TIMEOUT, TimeUnit.MINUTES)
                .writeTimeout(BuildConfig.NETWORK_TIMEOUT, TimeUnit.MINUTES)
                .build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}
