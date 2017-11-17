package app.splash

import android.accounts.AccountManager
import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.GoogleAuthUtil
import com.google.android.gms.common.AccountPicker
import org.stoyicker.dinger.R
import java.lang.ref.WeakReference

internal class UserEmailPropertySetterCoordinator(
        activity: Activity,
        private val splashEventTracker: SplashEventTracker,
        resultCallback: ResultCallback) {
    private val activityWeakRef by lazy { WeakReference(activity) }
    private val resultCallbackWeakRef by lazy { WeakReference(resultCallback) }

    fun actionDoSet() {
        activityWeakRef.get()?.apply {
            getSharedPreferences(
                    getString(R.string.preferences_file_core), Context.MODE_PRIVATE)!!
                    .getString(Companion.PREFERENCE_KEY_USER_PROVIDED_ACCOUNT, null).let {
                when (it) {
                    null -> AccountPicker.newChooseAccountIntent(
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
                                ?: resultCallbackWeakRef.get()?.onUserEmailAcquisitionFailed() }
                    else -> saveAndReportSuccess(it)
                }
            }
        }
    }

    fun onActivityResult(resultCode: Int, data: Intent?) {
        when (resultCode) {
            Activity.RESULT_OK -> saveAndReportSuccess(data?.getStringExtra(AccountManager.KEY_ACCOUNT_NAME))
            else -> resultCallbackWeakRef.get()?.onUserEmailAcquisitionFailed()
        }
    }

    private fun saveAndReportSuccess(value: String?) {
        splashEventTracker.apply {
            value.let {
                activityWeakRef.get()?.apply {
                    getSharedPreferences(getString(R.string.preferences_file_core), Context.MODE_PRIVATE)!!
                            .edit()
                            .putString(PREFERENCE_KEY_USER_PROVIDED_ACCOUNT, it)
                            .apply()
                }
                setUserProvidedAccount(it)
            }
            trackUserProvidedAccount()
        }
        resultCallbackWeakRef.get()?.onUserEmailPropertySet()
    }

    interface ResultCallback {
        fun onUserEmailPropertySet()

        fun onUserEmailAcquisitionFailed()
    }

    companion object {
        const val PREFERENCE_KEY_USER_PROVIDED_ACCOUNT =
                "org.stoyicker.dinger.USER_PROVIDED_ACCOUNT"
        const val REQUEST_CODE = 1
    }
}
