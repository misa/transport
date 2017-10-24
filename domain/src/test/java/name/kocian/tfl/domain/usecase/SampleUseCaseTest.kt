package name.kocian.tfl.domain.usecase

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import name.kocian.tfl.domain.repository.SampleRepository
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
class SampleUseCaseTest {

    @get:Rule
    @Suppress("unused")
    val rule: MockitoRule = MockitoJUnit.rule()

    private lateinit var sampleUseCase: SampleUseCase

    @Mock
    private lateinit var sampleRepository: SampleRepository

    @Before
    fun setUp() {
        sampleUseCase = SampleUseCase(
                sampleRepository,
                Schedulers.trampoline(),
                Schedulers.trampoline()
        )
    }

    @Test
    fun testBuildObservable() {
        val result = "test"
        `when`(sampleRepository.getTest()).thenReturn(Observable.just(result))

        sampleUseCase.asObservable()
                .test()
                .assertValueCount(1)
                .assertValue(result)
                .assertNoErrors()
                .assertComplete()
    }
}
