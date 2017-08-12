package data.tinder.auth

import com.squareup.moshi.Json
import org.stoyicker.dinger.data.BuildConfig

internal data class AuthRequestParameters(
        @field:Json(name = "id")
        private val facebookId: String,
        @field:Json(name = "token")
        private val token: String,
        @field:Json(name = "client_version")
        private val clientVersion: String = BuildConfig.TINDER_VERSION_NAME)
