package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserJob private constructor(
        @field:Json(name = "company")
        private val company: RecommendationUserJobCompany,
        @field:Json(name = "title")
        private val title: RecommendationUserJobTitle)
