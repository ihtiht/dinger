package data.tinder.recommendations

import com.squareup.moshi.Json
import java.util.Date

internal class DataRecommendationUser(
        @Json(name = "distance_mi")
        private val distanceMiles: Int,
        @Json(name = "common_connections")
        private val commonConnections: Array<Any>, // TODO Support common connections
        @Json(name = "connection_count")
        private val connectionCount: Int,
        @Json(name = "common_interests")
        private val commonInterests: Array<DataInterest>,
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
        private val photos: Array<DataRecommendationUserPhoto>,
        @Json(name = "instagram")
        private val instagram: DataRecommendationUserInstagram,
        @Json(name = "jobs")
        private val jobs: Array<DataRecommendationUserJob>,
        @Json(name = "schools")
        private val schools: Array<DataRecommendationUserSchool>,
        @Json(name = "teaser")
        private val teaser: DataRecommendationUserTeaser,
        @Json(name = "teasers")
        private val teasers: Array<DataRecommendationUserTeaser>,
        @Json(name = "s_number")
        private val sNumber: Int,
        @Json(name = "spotify_theme_track")
        private val spotifyThemeTrack: DataRecommendationUserSpotifyThemeTrack,
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
