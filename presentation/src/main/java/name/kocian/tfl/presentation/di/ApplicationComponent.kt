package name.kocian.tfl.presentation.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import name.kocian.tfl.datasource.di.NetworkModule
import name.kocian.tfl.device.di.NetworkManagerModule
import name.kocian.tfl.presentation.TflApplication

@AppScope
@Component(modules = [
    ApplicationModule::class,
    AndroidInjectionModule::class,
    ActivitiesModule::class,
    NetworkModule::class,
    NetworkManagerModule::class
])
interface ApplicationComponent {

    fun inject(app: TflApplication)
}
