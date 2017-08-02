package data.network.tinder.recommendation

import android.support.annotation.Px
import com.squareup.moshi.Json

internal class RecommendationUserSpotifyThemeTrackAlbumImage private constructor(
        @Json(name = "width")
        @Px
        private val width: Int,
        @Json(name = "url")
        private val url: String,
        @Json(name = "height")
        @Px
        private val height: Int)
