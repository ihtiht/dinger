package data.tinder.recommendations

import com.squareup.moshi.Json

internal class RecommendationResponse private constructor(
        @Json(name = "status")
        private val status: Int,
        @Json(name = "results")
        private val recommendations: Array<DataRecommendation>)
