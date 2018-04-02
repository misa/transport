package name.kocian.tfl.presentation.ui.status

import name.kocian.tfl.domain.entity.LineStatus
import name.kocian.tfl.presentation.model.StatusModel

class LineStatusModelMapping {
    fun toModel(lineStatus: LineStatus): StatusModel {
        return StatusModel(lineStatus.id, lineStatus.name, lineStatus.transportType,
                lineStatus.severity, lineStatus.severityTitle, lineStatus.description)
    }
}
