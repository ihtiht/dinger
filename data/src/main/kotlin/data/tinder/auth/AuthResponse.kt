package data.tinder.auth

import com.squareup.moshi.Json

internal class AuthResponse private constructor(
        @Json(name = "meta")
        private val authResponseMeta: AuthResponseMeta,
        @Json(name = "user")
        private val user: AuthResponseUser)
