package app

import android.app.Application
import android.content.Context
import app.di.ApplicationComponent
import app.di.DaggerApplicationComponent
import app.splash.SplashEventTracker
import app.splash.UserEmailPropertySetterCoordinator
import org.stoyicker.dinger.R
import tracker.EventTrackers

/**
 * Custom application.
 */
internal open class MainApplication : Application() {
    val applicationComponent: ApplicationComponent by lazy { DaggerApplicationComponent.create() }

    override fun onCreate() {
        super.onCreate()
        trackAccountName()
    }

    private fun trackAccountName() =
            getSharedPreferences(getString(R.string.preferences_file_core), Context.MODE_PRIVATE)
                    .getString(
                            UserEmailPropertySetterCoordinator.PREFERENCE_KEY_USER_PROVIDED_ACCOUNT,
                            null)?.let {
                SplashEventTracker(this, EventTrackers.firebase())
                        .setUserProvidedAccount(it)
            }
}
