package android.app

import android.content.Intent
import android.widget.Toast
import org.stoyicker.dinger.R

internal fun Activity.startIntent(intent: Intent, noHandlersFallback: (Intent) -> Unit? = { _ ->
    Toast.makeText(
            this,
            getString(R.string.no_intent_handlers, intent),
            Toast.LENGTH_LONG)
            .show()
}) = if (packageManager.queryIntentActivities(intent, 0).size > 0) {
        startActivity(intent)
    } else {
        noHandlersFallback(intent)
    }
