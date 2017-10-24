package name.kocian.tfl.presentation.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import io.reactivex.Scheduler
import name.kocian.tfl.datasource.di.NetworkModule
import name.kocian.tfl.domain.usecase.AbstractUseCase
import name.kocian.tfl.presentation.TflApplication
import javax.inject.Named

@AppScope
@Component(modules = arrayOf(
        ApplicationModule::class,
        AndroidInjectionModule::class,
        ActivitiesModule::class,
        NetworkModule::class
))
interface ApplicationComponent {

    fun inject(app: TflApplication)

    @Named(AbstractUseCase.SCHEDULER_WORKER)
    fun getWorkerScheduler(): Scheduler

    @Named(AbstractUseCase.SCHEDULER_RESULT)
    fun getResultScheduler(): Scheduler
}
