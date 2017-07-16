package data.network.tinder.auth

import com.squareup.moshi.Json

internal class AuthResponse private constructor(
        @Json(name = "meta")
        private val authResponseMeta: AuthResponseMeta,
        @Json(name = "user")
        val user: AuthResponseUser)
