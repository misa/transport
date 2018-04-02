package name.kocian.tfl.presentation.di

import android.content.Context
import dagger.Provides

@AppScope
@dagger.Module
class ApplicationModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }
}
