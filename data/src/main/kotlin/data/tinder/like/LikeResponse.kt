package data.tinder.like

import com.squareup.moshi.Json

internal data class LikeResponse(
        @Json(name = "something")
        val match: DataMatch,
        @Json(name = "something")
        val likesRemaining: Int
)
