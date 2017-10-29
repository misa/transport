package name.kocian.tfl.presentation.model

data class StatusModel(
        val id: String,
        val name: String,
        val transportType: String,
        val severity: Int,
        val severityTitle: String,
        val description: String?
)
