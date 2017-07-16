package data.network.tinder.recommendations

import com.squareup.moshi.Json

internal class DataRecommendationUserPhoto private constructor(
        @Json(name = "id")
        private val id: String,
        @Json(name = "url")
        private val url: String,
        @Json(name = "processedFiles")
        private val processedFiles: Array<DataRecommendationUserPhotoProcessedFile>)
