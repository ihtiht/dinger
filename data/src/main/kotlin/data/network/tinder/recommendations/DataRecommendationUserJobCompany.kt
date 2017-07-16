package data.network.tinder.recommendations

import com.squareup.moshi.Json

internal class DataRecommendationUserJobCompany private constructor(
        @Json(name = "name")
        private val name: String)
