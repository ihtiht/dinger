package data.network.tinder.like

import com.squareup.moshi.Json

internal data class LikeResponse(
        @Json(name = "something")
        val match: Match,
        @Json(name = "something")
        val likesRemaining: Int
)
