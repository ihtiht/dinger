package data.network.tinder.auth

import com.squareup.moshi.Json

internal class AuthResponse private constructor(
        @Json(name = "meta")
        val meta: AuthResponseMeta,
        @Json(name = "data")
        val data: AuthResponseData)
