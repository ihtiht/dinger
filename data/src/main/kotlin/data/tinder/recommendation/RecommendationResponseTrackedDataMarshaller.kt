package data.tinder.recommendation

import android.os.Bundle
import tracker.TrackedDataMarshaller

internal class RecommendationResponseTrackedDataMarshaller
    : TrackedDataMarshaller<RecommendationResponse> {
    override fun marshall(source: RecommendationResponse) = Bundle().apply {
        putString("message", source.message ?: "no_message")
        putString("status", source.status.toString())
    }
}
