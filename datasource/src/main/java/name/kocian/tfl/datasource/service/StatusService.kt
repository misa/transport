package name.kocian.tfl.datasource.service

import io.reactivex.Single
import name.kocian.tfl.datasource.dto.LineStatusDto
import retrofit2.http.GET

interface StatusService {

    @get:GET("/line/mode/tube,overground,dlr,tflrail,tram/status")
    val lineStatus: Single<List<LineStatusDto>>
}
