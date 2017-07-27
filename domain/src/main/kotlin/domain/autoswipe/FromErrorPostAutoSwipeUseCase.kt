package domain.autoswipe

import android.content.Context
import org.stoyicker.dinger.domain.R

class FromErrorPostAutoSwipeUseCase(context: Context) : PostAutoSwipeUseCase(context) {
    override fun provideDelayMillis(context: Context) = context.resources.getInteger(
            R.integer.sweep_from_error_delay_ms).toLong()
}
