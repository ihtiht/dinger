package data.tinder.like

import com.squareup.moshi.Json

internal class LikeResponse private constructor(
        @field:Json(name = "match")
        val match: Boolean)
