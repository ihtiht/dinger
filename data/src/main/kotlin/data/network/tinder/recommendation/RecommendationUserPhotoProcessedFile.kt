package data.network.tinder.recommendation

import android.support.annotation.Px
import com.squareup.moshi.Json

internal class RecommendationUserPhotoProcessedFile(
        @Json(name = "width")
        @Px
        private val widthPx: Int,
        @Json(name = "url")
        private val url: String,
        @Json(name = "height")
        @Px
        private val heightPx: Int)
