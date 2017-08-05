package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserPhoto private constructor(
        @field:Json(name = "id")
        private val id: String,
        @field:Json(name = "url")
        private val url: String,
        @field:Json(name = "processedFiles")
        private val processedFiles: Array<RecommendationUserPhotoProcessedFile>)
