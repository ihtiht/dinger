package data.tinder.recommendations

import com.squareup.moshi.Json
import java.util.Date

internal class DataRecommendationUserInstagram private constructor(
        @Json(name = "profile_picture")
        private val profilePictureUrl: String,
        @Json(name = "last_fetch_time")
        private val lastFetchTime: Date,
        @Json(name = "media_count")
        private val mediaCount: Int,
        @Json(name = "photos")
        private val photos: Array<DataRecommendationUserInstagramPhoto>,
        @Json(name = "completed_initial_fetch")
        private val completedInitialFetch: Boolean,
        @Json(name = "username")
        private val username: String)
