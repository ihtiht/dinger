package data.tinder.like

import com.squareup.moshi.Json

internal data class LikeResponse(
        @field:Json(name = "something")
        val match: Match,
        @field:Json(name = "something")
        val likesRemaining: Int
)
