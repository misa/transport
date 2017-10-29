package name.kocian.tfl.domain.entity

data class LineStatus(
        val id: String,
        val name: String,
        val transportType: String,
        val severity: Int,
        val severityTitle: String,
        val description: String?
)
