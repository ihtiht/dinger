package data.tinder.recommendations

import android.support.annotation.Px
import com.squareup.moshi.Json

internal class DataRecommendationUserSpotifyThemeTrackAlbumImage private constructor(
        @Json(name = "width")
        @Px
        private val width: Int,
        @Json(name = "url")
        private val url: String,
        @Json(name = "height")
        @Px
        private val height: Int)
