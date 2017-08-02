package data.network.tinder.recommendation

import com.squareup.moshi.Json
import org.stoyicker.dinger.data.BuildConfig

internal data class RecommendationRequestParameters(
        @Json(name = "locale")
        private val clientVersion: String = BuildConfig.TINDER_API_LOCALE)
