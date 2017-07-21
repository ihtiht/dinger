package app.splash

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.view.View
import app.MainApplication
import app.login.TinderLoginActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import org.stoyicker.dinger.R
import javax.inject.Inject

/**
 * A simple activity that acts as a splash screen.
 *
 * Note how, instead of using the content view to set the splash, we just set the splash as
 * background in the theme. This allows it to be shown without having to wait for the content view
 * to be drawn.
 */
internal class SplashActivity : Activity(), LoggedInCheckCoordinator.ResultCallback {
    @Inject
    lateinit var loggedInCheckCoordinator: LoggedInCheckCoordinator

    private lateinit var handler: Handler

    override fun onResume() {
        super.onResume()
        inject()
        scheduleContentOpening()
    }

    /**
     * Schedules the app content to be shown.
     */
    private fun scheduleContentOpening() {
        handler = Handler()
        handler.postDelayed({ fetchUserAccount() }, SHOW_TIME_MILLIS)
    }

    /**
     * Closes the splash and introduces the actual content of the app.
     */
    private fun fetchUserAccount() {
        if (!assertGooglePlayServicesAvailable()) {
            return
        }
        loggedInCheckCoordinator.actionDoCheck()
    }

    override fun onPause() {
        handler.removeCallbacksAndMessages(null)
        super.onPause()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    override fun onLoggedInUserFound() {
        doLogin()
    }

    override fun onLoggedInUserNotFound() {
        requestToken()
    }

    private fun inject() = (application as MainApplication).applicationComponent
            .newSplashComponent(LoggedInCheckModule(this))
            .inject(this)

    /**
     * Checks for Play Services availability (required for Firebase). On failure, shows a dialog
     * that closes the app when dismissed.
     * @return <code>true</code> if Google Play Services are all nice and good, <code>false</code>
     *         otherwise.
     */
    private fun assertGooglePlayServicesAvailable(): Boolean {
        GoogleApiAvailability.getInstance().also {
            val status = it.isGooglePlayServicesAvailable(this)
            if (status != ConnectionResult.SUCCESS) {
                it.getErrorDialog(this, status, 0).apply {
                    setCancelable(false)
                    setOnDismissListener { System.exit(status) }
                    show()
                    return false
                }
            }
        }
        return true
    }

    private fun requestToken() {
        TinderLoginActivity.getCallingIntent(this).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
        }
        finishAfterTransition()
        overridePendingTransition(R.anim.fade_in, 0)
    }

    private fun doLogin() {
        // TODO Logged in, open a new activity
    }

    private companion object {
        const val SHOW_TIME_MILLIS = 1000L
    }
}
