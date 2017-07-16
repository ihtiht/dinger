package data.network.tinder.recommendations

import android.support.annotation.Px
import com.squareup.moshi.Json

internal class DataRecommendationUserPhotoProcessedFile(
        @Json(name = "width")
        @Px
        private val widthPx: Int,
        @Json(name = "url")
        private val url: String,
        @Json(name = "height")
        @Px
        private val heightPx: Int)
