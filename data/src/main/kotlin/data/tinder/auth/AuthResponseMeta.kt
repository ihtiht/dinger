package data.tinder.auth

import com.squareup.moshi.Json

internal class AuthResponseMeta private constructor(
        @field:Json(name = "status")
        val status: Int)
