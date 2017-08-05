package data.network.tinder.auth

import com.squareup.moshi.Json

internal class AuthResponseData private constructor(
        @field:Json(name = "is_new_user")
        val isNewUser: Boolean,
        @field:Json(name = "api_token")
        val apiToken: String)
