package tracker

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

sealed class EventTracker {
    abstract fun trackRecommendationResponse(data: Bundle)

    internal object Firebase : EventTracker() {
        private lateinit var delegate: FirebaseAnalytics

        fun init(context: Context) {
            delegate = FirebaseAnalytics.getInstance(context)
        }

        override fun trackRecommendationResponse(data: Bundle) {
            delegate.logEvent(Event.RECOMMENDATION_RESPONSE.key, data)
        }
    }

    companion object {
        fun firebase(): EventTracker = EventTracker.Firebase
    }
}
