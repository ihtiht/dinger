package android.app

import android.content.Intent

internal fun Activity.startIntent(intent: Intent, noHandlersFallback: (Intent) -> Unit) {
    if (packageManager.queryIntentActivities(intent, 0).size > 0) {
        startActivity(intent)
    } else {
        noHandlersFallback(intent)
    }
}
