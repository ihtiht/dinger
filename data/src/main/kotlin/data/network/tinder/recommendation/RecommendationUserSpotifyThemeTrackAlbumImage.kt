package data.network.tinder.recommendation

import android.support.annotation.Px
import com.squareup.moshi.Json

internal class RecommendationUserSpotifyThemeTrackAlbumImage private constructor(
        @field:Json(name = "width")
        @Px
        private val width: Int,
        @field:Json(name = "url")
        private val url: String,
        @field:Json(name = "height")
        @Px
        private val height: Int)
