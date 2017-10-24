package name.kocian.tfl.domain.usecase

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AbstractUseCaseTest {

    @Test
    fun testAsObservable() {
        val abstractUseCase = TestUseCase()

        assertThat(abstractUseCase.processScheduler).isNotNull()
        assertThat(abstractUseCase.resultScheduler).isNotNull()
    }

    class TestUseCase : AbstractUseCase<String>(Schedulers.trampoline(), Schedulers.trampoline()) {
        override fun buildObservable(): Observable<String> {
            return Observable.just("test")
        }
    }
}
