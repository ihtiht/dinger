package data.network.tinder.recommendation

import com.squareup.moshi.Json
import java.util.*

internal class RecommendationUser(
        @field:Json(name = "distance_mi")
        private val distanceMiles: Int,
        @field:Json(name = "common_connections")
        private val commonConnections: Array<Any>, // TODO Support common connections
        @field:Json(name = "connection_count")
        private val connectionCount: Int,
        @field:Json(name = "common_interests")
        private val commonInterests: Array<RecommendationInterest>,
        @field:Json(name = "content_hash")
        private val contentHash: String,
        @field:Json(name = "_id")
        private val id: String,
        @field:Json(name = "birth_date")
        private val birthDate: Date,
        @field:Json(name = "name")
        private val name: String,
        @field:Json(name = "ping_time")
        private val pingTime: Date,
        @field:Json(name = "photos")
        private val photos: Array<RecommendationUserPhoto>,
        @field:Json(name = "instagram")
        private val instagram: RecommendationUserInstagram,
        @field:Json(name = "jobs")
        private val jobs: Array<RecommendationUserJob>,
        @field:Json(name = "schools")
        private val schools: Array<RecommendationUserSchool>,
        @field:Json(name = "teaser")
        private val teaser: RecommendationUserTeaser,
        @field:Json(name = "teasers")
        private val teasers: Array<RecommendationUserTeaser>,
        @field:Json(name = "s_number")
        private val sNumber: Int,
        @field:Json(name = "spotify_theme_track")
        private val spotifyThemeTrack: RecommendationUserSpotifyThemeTrack,
        @Gender
        @field:Json(name = "gender")
        private val gender: Int,
        @field:Json(name = "birth_date_info")
        private val birthDateInfo: String,
        @field:Json(name = "group_matched")
        private val groupMatched: Boolean) {
    companion object {
        const val GENDER_MALE = 0
        const val GENDER_FEMALE = 1
    }
}
