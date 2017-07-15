package data.tinder.auth

import com.squareup.moshi.Json

internal class AuthResponse private constructor(
        @Json(name = "meta")
        private val meta: Meta,
        @Json(name = "something")
        private val userJson: String)

private class Meta(
        @Json(name = "status")
        private val status: Int)

private class Payload(
        @Json(name = "is_new_user")
        private val isNewUser: Boolean,
        @Json(name = "api_token")
        private val apiToken: String)
