package data.autoswipe

import android.content.Context
import android.content.Intent
import domain.autoswipe.AutoSwipeIntentServiceStarterFactory

internal class AutoSwipeIntentServiceStarterFactoryImpl : AutoSwipeIntentServiceStarterFactory {
    override fun newBroadcast(context: Context): Intent {
        return Intent(context, AutoSwipeBroadcastReceiver::class.java)
    }
}
