package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserSpotifyThemeTrack private constructor(
        @Json(name = "artists")
        private val artists: Array<RecommendationUserSpotifyThemeTrackArtist>,
        @Json(name = "album")
        private val album: RecommendationUserSpotifyThemeTrackAlbum,
        @Json(name = "preview_url")
        private val previewUrl: String,
        @Json(name = "name")
        private val name: String,
        @Json(name = "id")
        private val id: String,
        @Json(name = "uri")
        private val uri: String)
