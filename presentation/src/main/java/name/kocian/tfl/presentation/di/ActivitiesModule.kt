package name.kocian.tfl.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import name.kocian.tfl.presentation.ui.sample.LineStatusModule
import name.kocian.tfl.presentation.ui.sample.MainActivity

@Module
@ActivityScope
abstract class ActivitiesModule {

    @Suppress("unused")
    @ContributesAndroidInjector(modules = [
        LineStatusModule::class
    ])
    abstract fun provideMainActivityInjector(): MainActivity
}
