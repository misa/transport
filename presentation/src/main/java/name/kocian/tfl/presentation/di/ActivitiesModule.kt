package name.kocian.tfl.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import name.kocian.tfl.presentation.ui.sample.MainActivity
import name.kocian.tfl.presentation.ui.sample.SampleModule

@Module
@ActivityScope
@Suppress("unused")
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = arrayOf(
            SampleModule::class
    ))
    abstract fun provideMainActivityInjector(): MainActivity
}
