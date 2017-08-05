package data.network.tinder.recommendation

import com.squareup.moshi.Json
import java.util.Date

internal class RecommendationUserInstagram private constructor(
        @field:Json(name = "profile_picture")
        private val profilePictureUrl: String,
        @field:Json(name = "last_fetch_time")
        private val lastFetchTime: Date,
        @field:Json(name = "media_count")
        private val mediaCount: Int,
        @field:Json(name = "photos")
        private val photos: Array<RecommendationUserInstagramPhoto>,
        @field:Json(name = "completed_initial_fetch")
        private val completedInitialFetch: Boolean,
        @field:Json(name = "username")
        private val username: String)
