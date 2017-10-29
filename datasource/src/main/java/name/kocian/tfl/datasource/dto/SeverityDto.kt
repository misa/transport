package name.kocian.tfl.datasource.dto

import com.google.gson.annotations.SerializedName

data class SeverityDto(
        @SerializedName("statusSeverity")
        val severity: Int,

        @SerializedName("statusSeverityDescription")
        val severityTitle: String,

        @SerializedName("reason")
        val description: String
)
