package domain.autoswipe

import android.content.Context
import org.stoyicker.dinger.domain.R

class ImmediatePostAutoSwipeUseCase(context: Context) : PostAutoSwipeUseCase(context) {
    override fun provideDelayMillis(context: Context) = context.resources.getInteger(
            R.integer.sweep_immediate_delay_ms).toLong()
}
