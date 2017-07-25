package domain.autoswipe

import android.content.Context
import android.content.Intent

interface AutoSwipeIntentFactory {
    fun newIntent(context: Context): Intent
}
