package data.network.tinder.auth

import com.squareup.moshi.Json

internal class AuthResponseMeta private constructor(
        @field:Json(name = "status")
        val status: Int)
