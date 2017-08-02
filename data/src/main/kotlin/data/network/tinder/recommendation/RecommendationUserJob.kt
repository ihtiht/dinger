package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserJob private constructor(
        @Json(name = "company")
        private val company: RecommendationUserJobCompany,
        @Json(name = "title")
        private val title: RecommendationUserJobTitle)
