package data.network.tinder.recommendation

import android.support.annotation.Px
import com.squareup.moshi.Json

internal class RecommendationUserPhotoProcessedFile(
        @field:Json(name = "width")
        @Px
        private val widthPx: Int,
        @field:Json(name = "url")
        private val url: String,
        @field:Json(name = "height")
        @Px
        private val heightPx: Int)
