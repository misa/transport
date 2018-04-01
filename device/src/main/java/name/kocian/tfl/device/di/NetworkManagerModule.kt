package name.kocian.tfl.device.di

import android.content.Context
import dagger.Provides
import name.kocian.tfl.device.network.NetworkManager
import name.kocian.tfl.device.network.NetworkManagerImpl

@dagger.Module
class NetworkManagerModule {

    @Provides
    fun provideNetworkManager(context: Context): NetworkManager {
        return NetworkManagerImpl(context)
    }
}
