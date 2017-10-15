package app.splash

import android.content.Context
import tracker.EventTracker

internal class SplashEventTracker(
        context: Context,
        private val eventTracker: EventTracker) {
    init {
        eventTracker.init(context)
    }

    fun trackUserProvidedAccount() = eventTracker.trackUserProvidedAccount()

    fun setUserProvidedAccount(value: String?) = eventTracker.setUserProvidedAccount(value)
}
