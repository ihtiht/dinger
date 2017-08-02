package data.network.tinder.recommendation

import com.squareup.moshi.Json
import java.util.*

internal class RecommendationUser(
        @Json(name = "distance_mi")
        private val distanceMiles: Int,
        @Json(name = "common_connections")
        private val commonConnections: Array<Any>, // TODO Support common connections
        @Json(name = "connection_count")
        private val connectionCount: Int,
        @Json(name = "common_interests")
        private val commonInterests: Array<RecommendationInterest>,
        @Json(name = "content_hash")
        private val contentHash: String,
        @Json(name = "_id")
        private val id: String,
        @Json(name = "birth_date")
        private val birthDate: Date,
        @Json(name = "name")
        private val name: String,
        @Json(name = "ping_time")
        private val pingTime: Date,
        @Json(name = "photos")
        private val photos: Array<RecommendationUserPhoto>,
        @Json(name = "instagram")
        private val instagram: RecommendationUserInstagram,
        @Json(name = "jobs")
        private val jobs: Array<RecommendationUserJob>,
        @Json(name = "schools")
        private val schools: Array<RecommendationUserSchool>,
        @Json(name = "teaser")
        private val teaser: RecommendationUserTeaser,
        @Json(name = "teasers")
        private val teasers: Array<RecommendationUserTeaser>,
        @Json(name = "s_number")
        private val sNumber: Int,
        @Json(name = "spotify_theme_track")
        private val spotifyThemeTrack: RecommendationUserSpotifyThemeTrack,
        @Gender
        @Json(name = "gender")
        private val gender: Int,
        @Json(name = "birth_date_info")
        private val birthDateInfo: String,
        @Json(name = "group_matched")
        private val groupMatched: Boolean) {
    companion object {
        const val GENDER_MALE = 0
        const val GENDER_FEMALE = 1
    }
}
