package data.network.tinder.recommendations

import com.squareup.moshi.Json

internal class DataRecommendation private constructor(
        @Json(name = "type")
        private val type: String,
        @Json(name = "group_matched")
        private val groupMatched: Boolean,
        @Json(name = "user")
        private val user: DataRecommendationUser)
