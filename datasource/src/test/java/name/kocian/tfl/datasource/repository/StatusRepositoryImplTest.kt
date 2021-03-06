package name.kocian.tfl.datasource.repository

import io.reactivex.Single
import name.kocian.tfl.datasource.dto.LineStatusDto
import name.kocian.tfl.datasource.dto.LineStatusDtoMapper
import name.kocian.tfl.datasource.dto.SeverityDto
import name.kocian.tfl.datasource.service.StatusService
import name.kocian.tfl.domain.repository.StatusRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.net.UnknownHostException
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class StatusRepositoryImplTest {
    private lateinit var repository: StatusRepository

    @Mock
    private lateinit var mockStatusService: StatusService

    @Before
    fun setUp() {
        repository = StatusRepositoryImpl(mockStatusService, LineStatusDtoMapper())
    }

    @Test
    fun getStatusesReturnsStatusText() {
        val lineStatuses = mutableListOf(SeverityDto(10, "severity", "description"))
        val tubeDto = LineStatusDto("id", "test", "type", lineStatuses)
        val response = Collections.singletonList(tubeDto)

        `when`(mockStatusService.lineStatus).thenReturn(Single.just(response))

        repository.getLineStatus()
                .test()
                .assertValueCount(1)
                .assertNoErrors()
                .assertComplete()
    }

    @Test
    fun getStatusesReceives500Response() {
        val response = UnknownHostException()

        `when`(mockStatusService.lineStatus).thenReturn(Single.error(response))

        repository.getLineStatus()
                .test()
                .assertError(Exception::class.java)
    }
}
