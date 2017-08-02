package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserPhoto private constructor(
        @Json(name = "id")
        private val id: String,
        @Json(name = "url")
        private val url: String,
        @Json(name = "processedFiles")
        private val processedFiles: Array<RecommendationUserPhotoProcessedFile>)
