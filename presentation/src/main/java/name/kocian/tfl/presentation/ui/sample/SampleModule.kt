package name.kocian.tfl.presentation.ui.sample

import dagger.Provides
import name.kocian.tfl.datasource.repository.SampleRepositoryImpl
import name.kocian.tfl.datasource.service.SampleService
import name.kocian.tfl.device.network.NetworkManager
import name.kocian.tfl.domain.repository.SampleRepository
import name.kocian.tfl.domain.usecase.SampleUseCase
import name.kocian.tfl.presentation.di.ActivityScope

@ActivityScope
@dagger.Module
class SampleModule {

    @Provides
    fun provideSampleRepository(sampleService: SampleService): SampleRepository {
        return SampleRepositoryImpl(sampleService)
    }

    @Provides
    fun provideSamplePresenter(sampleUseCase: SampleUseCase, networkManager: NetworkManager): SampleMvp.Presenter {
        return SamplePresenter(sampleUseCase, networkManager)
    }
}
