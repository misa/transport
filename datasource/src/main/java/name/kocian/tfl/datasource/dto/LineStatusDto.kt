package name.kocian.tfl.datasource.dto

import com.google.gson.annotations.SerializedName

data class LineStatusDto(
        @SerializedName("id")
        val id: String,

        @SerializedName("name")
        val name: String,

        @SerializedName("modeName")
        val transportType: String,

        @SerializedName("lineStatuses")
        val lineSeverity: List<SeverityDto>
)
