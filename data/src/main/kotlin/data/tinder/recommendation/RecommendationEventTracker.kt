package data.tinder.recommendation

import tracker.EventTracker
import tracker.TrackedDataMarshaller

internal class RecommendationEventTracker(
        private val eventTracker: EventTracker,
        private val recommendationResponseMarshaller: TrackedDataMarshaller<RecommendationResponse>) {
    fun track(response: RecommendationResponse) {
        eventTracker.trackRecommendationResponse(
                recommendationResponseMarshaller.marshall(response))
    }
}
