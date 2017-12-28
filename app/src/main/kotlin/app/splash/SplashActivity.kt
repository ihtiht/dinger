package app.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import app.MainApplication
import app.home.HomeActivity
import tinder.login.TinderLoginActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import javax.inject.Inject
/**
 * A simple activity that acts as a splash screen.
 *
 * Note how, instead of using the content view to set the splash, we just set the splash as
 * background in the theme. This allows it to be shown without having to wait for the content view
 * to be drawn.
 */
internal class SplashActivity : LoggedInCheckCoordinator.ResultCallback,
        UserEmailPropertySetterCoordinator.ResultCallback, VersionCheckCoordinator.ResultCallback,
        AppCompatActivity() {
    @Inject
    lateinit var loggedInCheckCoordinator: LoggedInCheckCoordinator
    @Inject
    lateinit var userEmailPropertySetterCoordinator: UserEmailPropertySetterCoordinator
    @Inject
    lateinit var versionCheckCoordinator: VersionCheckCoordinator
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        scheduleContentOpening()
    }

    override fun onDestroy() {
        loggedInCheckCoordinator.actionCancel()
        versionCheckCoordinator.actionCancel()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        versionCheckCoordinator.resume()
    }

    override fun onPause() {
        handler.removeCallbacksAndMessages(null)
        versionCheckCoordinator.pause()
        super.onPause()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            UserEmailPropertySetterCoordinator.REQUEST_CODE ->
                userEmailPropertySetterCoordinator.onActivityResult(resultCode, data)
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onUserEmailPropertySet() = versionCheckCoordinator.actionRun()

    override fun onUserEmailAcquisitionFailed() {
        if (!isFinishing) supportFinishAfterTransition()
    }

    override fun onVersionCheckCompleted() { loggedInCheckCoordinator.actionRun() }

    override fun onLoggedInUserFound() = continueLoggedIn()

    override fun onLoggedInUserNotFound() = requestToken()

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
        if (assertGooglePlayServicesAvailable()) {
            userEmailPropertySetterCoordinator.actionDoSet()
        }
    }

    private fun inject() = (application as MainApplication).applicationComponent
            .newSplashComponent(SplashModule(
                    activity = this,
                    loggedInCheckResultCallback = this,
                    userEmailPropertySetterCoordinatorResultCallback = this,
                    versionCheckCoordinatorResultCallback = this))
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
        supportFinishAfterTransition()
    }

    private fun continueLoggedIn() {
        HomeActivity.getCallingIntent(this).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
        }
        supportFinishAfterTransition()
    }

    private companion object {
        const val SHOW_TIME_MILLIS = 500L
    }
}

