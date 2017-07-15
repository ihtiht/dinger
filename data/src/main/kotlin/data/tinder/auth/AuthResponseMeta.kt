package data.tinder.auth

import com.squareup.moshi.Json

internal class AuthResponseMeta private constructor(
        @Json(name = "status")
        private val status: Int)
