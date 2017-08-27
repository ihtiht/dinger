package data.autoswipe

import dagger.Component
import data.crash.FirebaseCrashReporterModule
import javax.inject.Singleton

@Component(modules = arrayOf(FirebaseCrashReporterModule::class))
@Singleton
internal interface AutoSwipeComponent {
    fun inject(autoSwipeJobIntentService: AutoSwipeJobIntentService)
}
