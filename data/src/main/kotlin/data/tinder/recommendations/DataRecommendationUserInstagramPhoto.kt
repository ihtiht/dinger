package data.tinder.recommendations

import com.squareup.moshi.Json

internal class DataRecommendationUserInstagramPhoto private constructor(
        @Json(name = "link")
        private val link: String,
        @Json(name = "image")
        private val imageUrl: String,
        @Json(name = "thumbnail")
        private val thumbnailUrl: String,
        @Json(name = "ts")
        private val ts: String)
