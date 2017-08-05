package data.network.tinder.recommendation

import com.squareup.moshi.Json

internal class RecommendationUserSpotifyThemeTrack private constructor(
        @field:Json(name = "artists")
        private val artists: Array<RecommendationUserSpotifyThemeTrackArtist>,
        @field:Json(name = "album")
        private val album: RecommendationUserSpotifyThemeTrackAlbum,
        @field:Json(name = "preview_url")
        private val previewUrl: String,
        @field:Json(name = "name")
        private val name: String,
        @field:Json(name = "id")
        private val id: String,
        @field:Json(name = "uri")
        private val uri: String)
