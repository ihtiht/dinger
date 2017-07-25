package data.autoswipe

import android.content.Context
import android.content.Intent
import domain.autoswipe.AutoSwipeIntentFactory

internal class AutoSwipeIntentFactoryImpl : AutoSwipeIntentFactory {
    override fun newIntent(context: Context) = Intent(context, AutoSwipeIntentService::class.java)
}
