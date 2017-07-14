package data.tinder.recommendations

import com.squareup.moshi.Json

internal class RecommendationResponse(
        @Json(name = "something")
        private val message: String,
        @Json(name = "something")
        private val recommendations: List<DataRecommendation>)
