package data.tinder.like

import com.squareup.moshi.Json

internal class LikeResponse private constructor(
        @field:Json(name = "match")
        val match: Boolean,
        @field:Json(name = "likes_remaining")
        val likesRemaining: Int,
        @field:Json(name = "rate_limited_until")
        val rateLimitedUntil: Long?)
