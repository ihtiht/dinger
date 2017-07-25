package domain.autoswipe

import android.content.Context
import org.stoyicker.dinger.domain.R

class DelayedPostAutoSwipeUseCase(context: Context) : PostAutoSwipeUseCase(context) {
    override fun provideDelayMillis() = context.resources.getInteger(
            R.integer.sweep_delayed_delay_ms).toLong()
}
