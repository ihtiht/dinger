package app.alarmbanner

import app.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
@PerActivity
internal class AlarmBannerModule(private val activity: AlarmBannerActivity) {
    @Provides
    fun coordinator() = AlarmBannerCoordinator(activity)
}
