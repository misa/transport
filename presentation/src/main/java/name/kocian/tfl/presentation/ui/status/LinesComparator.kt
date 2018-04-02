package name.kocian.tfl.presentation.ui.status

import name.kocian.tfl.domain.entity.LineStatus

class LinesComparator : Comparator<LineStatus> {

    override fun compare(line1: LineStatus, line2: LineStatus): Int {
        // Push other services to the end
        if (line1.transportType == "dlr"
                || line1.transportType == "overground"
                || line1.transportType == "tflrail"
                || line1.transportType == "tram") {
            return 1
        }
        if (line2.transportType == "dlr"
                || line2.transportType == "overground"
                || line2.transportType == "tflrail"
                || line2.transportType == "tram") {
            return -1
        }

        // Tube - ascending order
        return line1.name.compareTo(line2.name)
    }
}
