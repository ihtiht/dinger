package domain.autoswipe

import android.content.Context
import android.content.Intent

interface AutoSwipeIntentServiceStarterFactory {
    fun newBroadcast(context: Context): Intent
}
