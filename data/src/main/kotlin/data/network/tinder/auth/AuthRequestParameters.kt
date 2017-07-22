package data.network.tinder.auth

import com.squareup.moshi.Json
import org.stoyicker.dinger.data.BuildConfig

internal data class AuthRequestParameters(
        @Json(name = "id")
        private val facebookId: String,
        @Json(name = "token")
        private val facebookVersion: String,
        @Json(name = "client_version")
        private val clientVersion: String = BuildConfig.CLIENT_VERSION)
