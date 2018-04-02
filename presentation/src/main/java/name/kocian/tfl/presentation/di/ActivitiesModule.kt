package name.kocian.tfl.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import name.kocian.tfl.presentation.ui.sample.LineStatusActivity
import name.kocian.tfl.presentation.ui.sample.LineStatusModule

@Module
@ActivityScope
abstract class ActivitiesModule {

    @Suppress("unused")
    @ContributesAndroidInjector(modules = [
        LineStatusModule::class
    ])
    abstract fun provideMainActivityInjector(): LineStatusActivity
}
