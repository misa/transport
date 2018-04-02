package name.kocian.tfl.datasource.dto

import name.kocian.tfl.domain.entity.LineStatus
import javax.inject.Inject

class LineStatusDtoMapper @Inject constructor() {

    fun toDomain(lineStatusDto: LineStatusDto) = LineStatus(
            lineStatusDto.id,
            lineStatusDto.name,
            lineStatusDto.transportType,
            lineStatusDto.lineSeverity[0].severity,
            lineStatusDto.lineSeverity[0].severityTitle,
            lineStatusDto.lineSeverity[0].description
    )
}
