package name.kocian.tfl.domain.usecase

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import name.kocian.tfl.domain.entity.LineStatus
import name.kocian.tfl.domain.repository.StatusRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
class StatusUseCaseTest {

    @get:Rule
    @Suppress("unused")
    val rule: MockitoRule = MockitoJUnit.rule()

    private lateinit var statusUseCase: StatusUseCase

    @Mock
    private lateinit var statusRepository: StatusRepository

    @Before
    fun setUp() {
        statusUseCase = StatusUseCase(
                statusRepository,
                Schedulers.trampoline(),
                Schedulers.trampoline()
        )
    }

    @Test
    fun testBuildObservable() {
        val result = ArrayList<LineStatus>()
        result.add(LineStatus("id", "name", "type", 10, "severity", "description"))
        `when`(statusRepository.getLineStatus()).thenReturn(Single.just(result))

        statusUseCase.build()
                .test()
                .assertValueCount(1)
                .assertValue(result)
                .assertNoErrors()
                .assertComplete()
    }
}
