package data.tinder.like

import com.squareup.moshi.Json

// TODO Try this without it being a data class
internal data class LikeResponse(
        @field:Json(name = "something")
        val match: Match,
        @field:Json(name = "something")
        val likesRemaining: Int
)
