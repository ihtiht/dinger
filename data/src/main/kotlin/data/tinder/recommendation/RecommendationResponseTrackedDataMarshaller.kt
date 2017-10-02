package data.tinder.recommendation

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import tracker.TrackedDataMarshaller

internal class RecommendationResponseTrackedDataMarshaller
    : TrackedDataMarshaller<RecommendationResponse> {
    override fun marshall(source: RecommendationResponse) = Bundle().apply {
        putString("message", source.message ?: "no_message")
        putString("status", source.status.toString())
        System.currentTimeMillis().let {
            putLong("date", it)
            putString("date_string", SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(it))
        }
    }
}
