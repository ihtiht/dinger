package data.tinder.auth

import com.squareup.moshi.Json

internal data class AuthenticationRequest(
        @Json(name = "something")
        private val facebookId: String,
        @Json(name = "something")
        private val facebookToken: String,
        @Json(name = "something")
        private val locale: String = "en")
