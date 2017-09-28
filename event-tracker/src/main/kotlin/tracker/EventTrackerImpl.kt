package tracker

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

internal sealed class EventTrackerImpl : EventTracker {
    /**
     * Firebase is a singleton, but other event trackers which can be instantiated with different
     * ids would not be.
     */
    object Firebase : EventTrackerImpl() {
        private lateinit var delegate: FirebaseAnalytics

        override fun init(context: Context) {
            delegate = FirebaseAnalytics.getInstance(context)
        }

        override fun trackRecommendationResponse(data: Bundle) {
            delegate.logEvent(Event.RECOMMENDATION_RESPONSE.key, data)
        }
    }
}
