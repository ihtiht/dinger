package data.network.tinder.auth

import com.squareup.moshi.Json

internal data class AuthRequestParameters(
        @Json(name = "id")
        private val facebookId: String,
        @Json(name = "token")
        private val facebookVersion: String,
        @Json(name = "client_version")
        private val clientVersion: String = "7.3.1") // TODO have this be dynamic
