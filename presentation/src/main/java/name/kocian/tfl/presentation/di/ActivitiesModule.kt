package name.kocian.tfl.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import name.kocian.tfl.presentation.ui.sample.LineStatusModule
import name.kocian.tfl.presentation.ui.sample.MainActivity

@Module
@ActivityScope
@Suppress("unused")
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = arrayOf(
            LineStatusModule::class
    ))
    abstract fun provideMainActivityInjector(): MainActivity
}
