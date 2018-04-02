package name.kocian.tfl.presentation.ui.status

import dagger.Provides
import name.kocian.tfl.datasource.dto.LineStatusDtoMapper
import name.kocian.tfl.datasource.repository.StatusRepositoryImpl
import name.kocian.tfl.datasource.service.StatusService
import name.kocian.tfl.device.network.NetworkManager
import name.kocian.tfl.domain.repository.StatusRepository
import name.kocian.tfl.domain.usecase.StatusUseCase
import name.kocian.tfl.presentation.di.ActivityScope

@ActivityScope
@dagger.Module
class LineStatusModule {

    @Provides
    fun provideStatusRepository(
            statusService: StatusService,
            lineStatusMapper: LineStatusDtoMapper): StatusRepository {
        return StatusRepositoryImpl(statusService, lineStatusMapper)
    }

    @Provides
    fun provideStatusPresenter(statusUseCase: StatusUseCase, networkManager: NetworkManager): LineStatusMvp.Presenter {
        return LineStatusPresenter(statusUseCase, networkManager)
    }
}
