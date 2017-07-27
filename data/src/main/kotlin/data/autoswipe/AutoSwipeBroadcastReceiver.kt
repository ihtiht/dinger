package data.autoswipe

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

internal class AutoSwipeBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { it.startService(AutoSwipeIntentService.getCallingIntent(it)) }
    }

    companion object {
        fun getCallingIntent(context: Context)
                = Intent(context, AutoSwipeBroadcastReceiver::class.java)
    }
}
