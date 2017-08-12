package data.tinder.auth

import com.squareup.moshi.Json

internal class AuthResponse private constructor(
        @field:Json(name = "meta")
        val meta: AuthResponseMeta,
        @field:Json(name = "data")
        val data: AuthResponseData)
