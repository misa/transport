package name.kocian.tfl.presentation.di

import android.content.Context
import dagger.Provides
import name.kocian.tfl.device.network.NetworkManager
import name.kocian.tfl.device.network.NetworkManagerImpl

@AppScope
@dagger.Module
class ApplicationModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideNetworkManager(context: Context): NetworkManager {
        return NetworkManagerImpl(context)
    }
}
