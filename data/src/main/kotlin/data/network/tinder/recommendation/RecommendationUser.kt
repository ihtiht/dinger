package data.network.tinder.recommendation

import com.squareup.moshi.Json
import java.util.*

internal class RecommendationUser private constructor(
        @field:Json(name = "distance_mi")
        val distanceMiles: Int,
        @field:Json(name = "common_connections")
        val commonConnections: Array<Any?>, // TODO Support common connections
        @field:Json(name = "connection_count")
        val connectionCount: Int,
        @field:Json(name = "common_interests")
        val commonInterests: Array<RecommendationInterest>,
        @field:Json(name = "content_hash")
        val contentHash: String,
        @field:Json(name = "_id")
        val id: String,
        @field:Json(name = "birth_date")
        val birthDate: Date,
        @field:Json(name = "name")
        val name: String,
        @field:Json(name = "ping_time")
        val pingTime: Date,
        @field:Json(name = "photos")
        val photos: Array<RecommendationUserPhoto>,
        @field:Json(name = "instagram")
        val instagram: RecommendationUserInstagram,
        @field:Json(name = "jobs")
        val jobs: Array<RecommendationUserJob>,
        @field:Json(name = "schools")
        val schools: Array<RecommendationUserSchool>,
        @field:Json(name = "teaser")
        val teaser: RecommendationUserTeaser,
        @field:Json(name = "teasers")
        val teasers: Array<RecommendationUserTeaser>,
        @field:Json(name = "s_number")
        val sNumber: Int,
        @field:Json(name = "spotify_theme_track")
        val spotifyThemeTrack: RecommendationUserSpotifyThemeTrack,
        @Gender
        @field:Json(name = "gender")
        val gender: Int,
        @field:Json(name = "birth_date_info")
        val birthDateInfo: String,
        @field:Json(name = "group_matched")
        val groupMatched: Boolean) {
    companion object {
        const val GENDER_MALE = 0
        const val GENDER_FEMALE = 1
    }
}
