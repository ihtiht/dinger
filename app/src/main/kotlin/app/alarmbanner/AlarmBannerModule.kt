package app.alarmbanner

import app.di.PerActivity
import dagger.Module
import dagger.Provides
import domain.exec.PostExecutionSchedulerProvider

@Module
@PerActivity
internal class AlarmBannerModule(private val activity: AlarmBannerActivity) {
    @Provides
    fun coordinator(postExecutionSchedulerProvider: PostExecutionSchedulerProvider)
            = AlarmBannerCoordinator(activity, postExecutionSchedulerProvider)
}
