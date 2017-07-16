package data.network.tinder.recommendations

import com.squareup.moshi.Json

internal class DataRecommendationUserSpotifyThemeTrack private constructor(
        @Json(name = "artists")
        private val artists: Array<DataRecommendationUserSpotifyThemeTrackArtist>,
        @Json(name = "album")
        private val album: DataRecommendationUserSpotifyThemeTrackAlbum,
        @Json(name = "preview_url")
        private val previewUrl: String,
        @Json(name = "name")
        private val name: String,
        @Json(name = "id")
        private val id: String,
        @Json(name = "uri")
        private val uri: String)
