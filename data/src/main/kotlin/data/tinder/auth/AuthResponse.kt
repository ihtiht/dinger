package data.tinder.auth

import com.squareup.moshi.Json

internal data class AuthResponse(
        @Json(name = "something")
        val token: String,
        @Json(name = "something")
        val userJson: String)
