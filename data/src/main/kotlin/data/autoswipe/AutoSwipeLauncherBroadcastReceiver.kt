package data.autoswipe

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

internal class AutoSwipeLauncherBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { AutoSwipeJobIntentService.trigger(it) }
    }

    companion object {
        fun getCallingIntent(context: Context)
                = Intent(context, AutoSwipeLauncherBroadcastReceiver::class.java)
    }
}
