package data.network.tinder.recommendations

import com.squareup.moshi.Json

internal class DataRecommendationUserJob private constructor(
        @Json(name = "company")
        private val company: DataRecommendationUserJobCompany,
        @Json(name = "title")
        private val title: DataRecommendationUserJobTitle)
