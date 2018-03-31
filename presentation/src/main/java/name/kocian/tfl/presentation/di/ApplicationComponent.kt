package name.kocian.tfl.presentation.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import name.kocian.tfl.datasource.di.NetworkModule
import name.kocian.tfl.presentation.TflApplication

@AppScope
@Component(modules = arrayOf(
        ApplicationModule::class,
        AndroidInjectionModule::class,
        ActivitiesModule::class,
        NetworkModule::class
))
interface ApplicationComponent {

    fun inject(app: TflApplication)
}
