package name.kocian.tfl.datasource.dto

import name.kocian.tfl.domain.entity.LineStatus
import javax.inject.Inject

class LineStatusDtoMapper @Inject constructor() {

    fun toDomain(lineStatusDto: LineStatusDto) = LineStatus(
            lineStatusDto.id,
            lineStatusDto.name,
            lineStatusDto.transportType,
            lineStatusDto.lineSeverity.get(0).severity,
            lineStatusDto.lineSeverity.get(0).severityTitle,
            lineStatusDto.lineSeverity.get(0).description
    )
}
