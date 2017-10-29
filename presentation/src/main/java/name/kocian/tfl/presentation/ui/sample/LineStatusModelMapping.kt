package name.kocian.tfl.presentation.ui.sample

import name.kocian.tfl.domain.entity.LineStatus
import name.kocian.tfl.presentation.model.StatusModel

class LineStatusModelMapping {
    fun toModel(lineStatus: LineStatus): StatusModel {
        return StatusModel(lineStatus.id, lineStatus.name, lineStatus.transportType)
    }
}
