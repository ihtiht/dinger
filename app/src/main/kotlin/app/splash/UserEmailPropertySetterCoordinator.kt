package app.splash

import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import com.google.android.gms.auth.GoogleAuthUtil
import com.google.android.gms.common.AccountPicker
import java.lang.ref.WeakReference

internal class UserEmailPropertySetterCoordinator(
        activity: Activity,
        private val splashEventTracker: SplashEventTracker,
        private val resultCallback: ResultCallback) {
    private val activityWeakRef = WeakReference(activity)

    fun actionDoSet() {
        AccountPicker.newChooseAccountIntent(
                null,
                null,
                arrayOf(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE),
                true,
                null,
                null,
                null,
                null).let {
            activityWeakRef.get()
                    ?.startActivityForResult(it, Companion.REQUEST_CODE)
                    ?: resultCallback.onUserEmailAcquisitionFailed()
        }
    }

    fun onActivityResult(resultCode: Int, data: Intent?) {
        when (resultCode) {
            Activity.RESULT_OK -> {
                data?.getStringExtra(AccountManager.KEY_ACCOUNT_NAME)
                // TODO Set the property
                splashEventTracker.trackUserProvidedAccount()
                resultCallback.onUserEmailPropertySet()
            }
            else -> {
                resultCallback.onUserEmailAcquisitionFailed()
            }
        }
    }

    interface ResultCallback {
        fun onUserEmailPropertySet()

        fun onUserEmailAcquisitionFailed()
    }

    companion object {
        const val REQUEST_CODE = 1
    }
}
