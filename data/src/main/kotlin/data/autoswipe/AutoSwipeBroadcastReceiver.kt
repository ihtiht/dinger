package data.autoswipe

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

internal class AutoSwipeBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.startService(Intent(context, AutoSwipeIntentService::class.java))
    }
}
