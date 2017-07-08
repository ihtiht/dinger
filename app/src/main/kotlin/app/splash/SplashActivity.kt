package app.splash

import android.app.Activity
import android.content.Intent
import android.os.Handler
import app.login.LoginActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import org.stoyicker.dinger.BuildConfig
import org.stoyicker.dinger.R

/**
 * A simple activity that acts as a splash screen.
 *
 * Note how, instead of using the content view to set the splash, we just set the splash as
 * background in the theme. This allows it to be shown without having to wait for the content view
 * to be drawn.
 */
internal class SplashActivity : Activity() {
    private lateinit var handler: Handler

    override fun onResume() {
        super.onResume()
        scheduleContentOpening()
    }

    /**
     * Schedules the app content to be shown.
     */
    private fun scheduleContentOpening() {
        handler = Handler()
        handler.postDelayed({ openContent() }, SHOW_TIME_MILLIS)
    }

    /**
     * Closes the splash and introduces the actual content of the app.
     */
    private fun openContent() {
        assertGooglePlayServicesAvailable()
        if (true) { // TODO !isLoggedIn
            LoginActivity.getCallingIntent(this).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(this)
            }
            finishAfterTransition()
            overridePendingTransition(R.anim.fade_in, 0)
        }
    }

    override fun onPause() {
        handler.removeCallbacksAndMessages(null)
        super.onPause()
    }

    /**
     * Checks for Play Services availability (required for Firebase). On failure, shows a dialog
     * and closes the app.
     */
    private fun assertGooglePlayServicesAvailable() {
        GoogleApiAvailability.getInstance().also {
            val status = it.isGooglePlayServicesAvailable(this)
            if (!BuildConfig.DEBUG && status != ConnectionResult.SUCCESS) {
                it.getErrorDialog(this, status, 0).apply {
                    setCancelable(false)
                    setOnDismissListener { System.exit(status) }
                    show()
                }
            }
        }
    }

    private companion object {
        const val SHOW_TIME_MILLIS = 1000L
    }
}
