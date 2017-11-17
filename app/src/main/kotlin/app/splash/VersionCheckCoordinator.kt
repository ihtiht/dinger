package app.splash

import android.app.Activity
import android.support.v7.app.AlertDialog
import java.lang.ref.WeakReference

internal class VersionCheckCoordinator(activity: Activity, resultCallback: ResultCallback) {
    private val activityWeakRef by lazy { WeakReference(activity) }
    private val resultCallbackWeakRef by lazy { WeakReference(resultCallback) }
    private var dialog: AlertDialog? = null
    private var wasShowing = false

    fun actionRun() {
        activityWeakRef.get()?.let {
            if (!it.isFinishing) {
                dialog = AlertDialog.Builder(it)
                        .setTitle("Update available")
                        .setMessage("There is an update available for the app. Older versions are now unsupported.")
                        .setPositiveButton("Download", { _, _ -> System.out.println("clicked") })
                        .setOnDismissListener {
                            wasShowing = false
                            resultCallbackWeakRef.get()?.onVersionCheckCompleted()
                        }
                        .show()
            }
        }
    }

    fun resume() {
        if (wasShowing) {
            dialog?.show()
        }
    }

    fun pause() {
        wasShowing = dialog?.isShowing ?: false
        dialog?.dismiss()
    }

    fun actionCancel() {
//        useCase?.dispose()
        dialog?.dismiss()
        wasShowing = false
    }

    interface ResultCallback {
        fun onVersionCheckCompleted()
    }
}
